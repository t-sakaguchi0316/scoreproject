package tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * フロントコントローラ（*.actionにマッチ）
 */
@WebServlet(urlPatterns = {"*.action"})
public class FrontController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        System.out.println("FrontController!");

        try {
            // リクエストされたパスを取得
            String path = request.getServletPath().substring(1);  // 例: "chapter23/Search.action"

            // アクションクラスの名前に変換
            String name = path.replace(".a", "A").replace('/', '.');  // 例: "chapter23.SearchAction"

            // アクションクラスを動的にロードしてインスタンス生成
            Action action = (Action) Class.forName(name)
                .getDeclaredConstructor()
                .newInstance();

            // アクションを実行（戻り値はなし）
            action.execute(request, response);

            // 各アクションクラス側で forward などを行う前提

        } catch (Exception e) {
            // エラー出力
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<h2>エラー発生</h2>");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doPost(request, response);
    }
}
