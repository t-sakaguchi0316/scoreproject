<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<p><label>科目情報</label></p>
<label>入学年度</label>
<label>クラス</label>
<label>科目</label>
<p>
<select name="f1">
<option value="" selected>---</option>
</select>
<select name="f2">
<option value="" selected>---</option>
</select>
<select name="f3">
<option value="" selected>---</option>
</select>
</p>
<input type="submit" value="検索">
<input type="hidden" name="f" value="st">
<p><label>学生情報</label></p>
<label>学生番号</label>
<input type="text" name="f4" value="${f4}">
<input type="submit" value="検索">
