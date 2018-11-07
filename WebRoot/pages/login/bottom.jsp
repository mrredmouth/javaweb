<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<hr/>

静态包含：@include		翻译阶段将jsp页面合并，只有一个servlet类。
<br/>
动态包含：jsp:include	每一个jsp页面单独翻译成一个servlet类；需要传递参数时，只能用此方式。