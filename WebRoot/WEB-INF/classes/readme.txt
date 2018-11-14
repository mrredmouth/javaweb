1、服务器：
	1）web服务器，轻量级，没有完全实现JavaEE的EJB规范，tomcat，Jetty等；
	2）应用服务器，重量级，完全支持JavaEE的EJB规范，TomEE，JBoss，Weblogic，WebSphere等；
	tomcat小，但是功能很强大，是目前主流的；

2、原始的部署到tomcat：
	在eclipse中，ctrl+c根目录，webRoot，整体拷贝到tomcat的F:\apache-tomcat-7.0.69\webapps目录，
	然后启动tomcat（bin目录的start*.bat）。然后即可访问。

2、get和post时默认传参使用 ISO-8859-I 的编码规则。中文乱码问题解决：
	post方式解决：在所有参数最前面，req.setCharacterEncoding("UTF-8");
	get方式解决：在tomcat的server.xml中修改，<Connector元素，默认有个属性URIEcoding="ISO-8859-1"(没写也默认存在)。改成URIEcoding="UTF-8"。
	
3、非线程安全，不能使用全局变量，单例的servlet对每个请求只会有一个空间给全局变量，可以设置局部变量，但性能降低
struts1和spring mvc都是非线程安全；struts2线程安全，每一个请求都是一个对象。

4、一次会话：打开浏览器，从打开某个网站开始，然后点点点，最后关闭网页，会话结束。
	会话Cookie：一次会话结束，则Cookie消失。也就是session。
	持久化Cookie：可以保存指定时间，一周，一个月等。Cookie对象.setMaxAge(int seconds)；
			seconds>0看servlet下的cookie的API，存活这么久
			seconds=0立即删除此对象
			seconds<0表示会话Cookie，存活到会话结束
	Cookie只会共享给最后一个斜线/之前的路径，类似于同包可用，需要设置path和domain
	
5、session，生命周期是一个会话Cookie周期，关闭之后消失。（session就是一个特殊的cookie）
	通过JSessionId，找到存在服务端的session（kv键值对），类似于开房门牌号。
	如果多台服务器共享session，要序列化，实现Serializable，才能在网络上传输
	session可以设置超时管理（用户的两次操作之间的时间，超过则销毁，自动注销。默认30分钟），session对象.setMaxInactive...

6、原生的JDBC【贾琏欲执事】：
	1：加载驱动
	2：创建连接对象connection
	3：sql语句
	4：执行语句
	5：释放connection资源

7、PrepareStatement预编译语句
	预编译池中有，就不生成语句等。mysql不支持预编译，oracle支持。oracle秒杀的节奏。
	mysql不支持预编译池，不支持预编译性能优化（性能同statement）；mysql不支持批处理操作addBatch。
	但是可以通过加载驱动，来支持批量处理，只对prepareStatement的批量处理有效。

其他：	
	web-inf目录是受保护的目录，外部资源无法访问；
	直接new的java project只是java SE，servlet是JavaEE的规范，要额外导入包；
	hosts文件：C:\WINDOWS\system32\drivers\etc；
	Code:500,后端java代码有问题
	set serveroutput on;设置plsql控制台输出能够打印
	mht文件，用ie生成，样式图片等等都可以看到；
	druid，德鲁伊，世界上最好的连接池。参照了DBCP的设计。
	shift+alt+r 修改所有选中的变量名，用到的地方一起修改
	java中的区间都是前闭后开[a,b)。
	写模板template，写代码时输入缩写，快速提示：preference，java，editor，templates然后new一个，
	内省机制：Introspector，查看和操作javabean中的属性。
	某个对象的方法可能有返回值，快速创建返回对象，选中方法，按ctrl+1，然后创建返回至类型的一个对象来接收。
	
	
	