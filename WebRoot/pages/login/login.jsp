<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="head.jsp" %>
<%--JSP三大指令：page,include,taglib --%>

	<!-- JSTL:taglib,EL:express language -->
	<!-- html注释，jsp仍然翻译到servlet类中，再解析到页面，也页面源代码中仍可见 -->
	<%-- jsp注释，jsp不会翻译到servlet中，页面源代码中也没有 --%>
	<%-- 每访问一个jsp，web容器都会生成对应的java和class文件，
		F:\apache-tomcat-7.0.90-demo\work\Catalina\localhost\_\org\apache\jsp\pages\login\login_jsp.java --%>

	<h4>jsp翻译成servlet类，login_jsp.java</h4>
	<% //java代码
		_name = "jspUserName";
		Boolean man = true;
	 %> <%--转为java里：原封不动展示，放在login_jsp.java类的_jspService方法里，作为局部变量，包括//注释 --%>
	<%=_name %> <%--转为java里：out.print(name),输出了下面的全局变量name的值，被赋值了Honny --%>
	<%! private String _name;
		public void doWork(){}
	 %><%--加了感叹号，此里面的全部搬到_jspService方法外面，作为的是全局变量、外部方法 --%>
	 
	<hr>
	<h4>四个作用域的数据</h4>
	<%--jsp九大内置对象，可以在jsp页面直接使用：
	request,response,pageContext,session,exception,application,config,out,page --%>
	<%--四大作用域对象 ，若jsp中包含java代码，会在编译的时候在临时目录生成java和class文件。此四个对象设属性在编译时就会被监听到--%>
	<%	pageContext.setAttribute("userMsg", "pageContextMsg");
		request.setAttribute("userMsg", "requestMsg");
		session.setAttribute("userMsg", "sessionMsg");
		application.setAttribute("userMsg", "applicationMsg");
	%>
	pageContext：<%=pageContext.getAttribute("userMsg") %><br/>
	request：<%=request.getAttribute("userMsg") %><br/>
	session：<%=session.getAttribute("userMsg") %><br/>
	application：<%=application.getAttribute("userMsg") %><br/>
	pageContext的findAttribute()方法，从外一直往里找：<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;jsp表达式：<%=pageContext.findAttribute("userMsg")==null?"":pageContext.findAttribute("userMsg") %><br/>
	&emsp;el表达式(原理同findAttribute，按顺序从作用域中获取指定属性的值)：<br/>
	${userMsg }<br/>
	三种方式获取属性：${userLogin }<br/>
		userLogin.list:   ${userLogin.list },&nbsp;<br/>
		userLogin[list]:  ${userLogin["list"] },&nbsp;<br/>
		userLogin.getList:${userLogin.getList() }<br/>
		userLogin.map.companyName:${userLogin.map.companyName }
	<hr/>
	<h4>JSTL表达式：</h4>
	<%--userLogin.list.size() > 0表达式的值存入变量listSizeResult中，下面直接可以用el取--%>
	<c:if test="${userLogin.list.size() > 0 }" var="listSizeResult" scope="page"></c:if>
	${listSizeResult }<br/>
	<c:if test="${listSizeResult }" >list个数：${userLogin.list.size() }</c:if><br/>
	<c:choose>
		<c:when test="${userLogin.age < 20 }">age<20</c:when>
		<c:when test="${userLogin.age == 20 }">age=20</c:when>
		<c:otherwise>age>20</c:otherwise>
	</c:choose><br/>
	<c:forEach var="ele" begin="1" end="10" step="1">
		${ele }
	</c:forEach><br/>
	<c:forEach items="${userLogin.list }" var="ele">
		${ele }
	</c:forEach><br/>
	fmt标签：北京时间<fmt:formatDate value="${nowDate }" pattern="yyyy-MM-dd HH:mm:ss"/><br/>
	
	<hr>
	<form action="/ccg/upload" method="post" enctype="multipart/form-data">
		<%	pageContext.setAttribute("_userName", "Tonny");%>
		<span style="color:red;">${errorMsg }</span><br/>
		<input type="text" name="userName" id="userName" placeholder="请输入用户名" value="${user.userName }"/><br/>
		<input type="password" name="password" id="password" placeholder="password" value="${user.password }"/><br/>
		<input type="file" name="headImage" id="headImage" accept="image/*" /> <br/>
		<img src="${user.headImage.imageUrl }" alt="${user.headImage.imageName }"/>
		<input type="submit" id="login" value="登陆" />
	</form>
	
	<hr>
	<h4>下载资源：</h4>
	<a href="/down?fileName=01_HTML-JS-新.xlsx" >01_HTML-JS-新.xlsx</a>
	
	
<jsp:include page="bottom.jsp">
	<jsp:param value="hahha" name="bottomName"/>
</jsp:include>
<%@ include file="foot.jsp" %>