/*!
 * 1,2,3、创建对象(三种方式)
 * 4、对象属性的获取，删除，修改
 * 5、对象属性的遍历
 * 6、数组遍历三种方式
 * 7、对象的序列化 ：
 * 8、创建函数(两种方式)
 */
console.log("======================创建对象========================");
//1、创建对象方式一：new Object()
var obj1 = new Object();
obj1.name = 'Tom1';
obj1.age = 11;
//2、创建对象方式二：对象字面量{}，是一种隐式的new。对象字面量有两种：简单字面量、嵌套字面量。
//2.1、简单字面量
var obj2 = {};	
obj2.name = 'Tom2';
obj2.age = 12;
obj2.getName = function(){
	return this.name;
}
//2.2、嵌套字面量(推荐使用)，。内容是json格式
var obj3 = {	
	name:'Tom3',
	age:13,
	getName:function(){
		console.log(this.name);
		return this.name;
	},
	'name stu':'name Tonny',//key的值必须加引号：如果里面含有空格；
	'name-stu':'name-Tonny',//key的值必须加引号：如果含有连接符等特殊字符；
	'class':'User'//key的值必须加引号：如果是关键字for,class等；
};
//3、创建对象方式三：构造函数 + new实例化。构造函数首字母要大写。
function Person(name,age){
	this.name = name;
	this.age = age;
	this.getName = function(){
		return this.name;
	}
}
var person1 = new Person("Tom4",24);
console.log(person1.getName());

function person(name,age){
	var obj = new Object();
	obj.name = name;
	obj.age = age;
	return obj;
}
var person2 = person("Tom5",25);
console.log(person2.name);


/*4、测试对象属性的获取，删除，修改===============*/
console.log("obj's attributes...")
var testObj = {};	
testObj.name = 'Tom2';
testObj.age = 12;
testObj.address = 'shanghai';
testObj.getName = function(){
	return this.name;
}
/*4.1 获取属性,两种种方式 :
* 	. 
* 	[]
*/
console.log("testObj.name:" + testObj.name); //点找属性，不加引号
console.log("testObj['name']:" + testObj['name']); //数组找变量，加引号

//4.2删除属性：delete
delete testObj.name;

/*4.3检测属性,三种方式 :
* 	in
* 	hasOwnProperty
* 	!= undefined 
*/
console.log(name in testObj); //name指属性，不加引号
console.log(testObj.hasOwnProperty("age")); //age指变量，要加引号
console.log(testObj.name !== 'undefined');  //= 复制；==、!= 值是否相等；===、!== 值和类型是否相等；

/*5、对象属性的遍历===============*/
/*对象遍历属性:for..in
* 	对于对象，根据key遍历；
* 	对于数组，根据索引(0开始)遍历；
*/
for(a in testObj){ //a表示对象的key，取对应的value用testObj[a]
	console.log(a + ":" + testObj[a]);
}
/*6、 数组遍历===============*/
/*
* 数组遍历三种方式：
* 	for..in
* 	普通for循环：同步的。必须循环拿到了，下面才可访问的时候，用此方式。
* 	$.each：异步的。拿到数据以后，进行的遍历是异步的，响应速度比较快。
*/
console.log("for..in方式");
var arr = [{x:1},{y:2},{z:3}];	//数组定义
for(a in arr){
	console.log(a + ":");
	console.log(arr[a]);
}
console.log("for普通方式");
for(var i=0;i<arr.length;i++){
	console.log(i + ":");
	console.log(arr[i]);
}
console.log("$.each方式");
$.each(arr,function(index, item) {
	console.log(index + ":");
	console.log(item);
})

/*7、对象的序列化 ： JSON.parse() 和 JSON.stringify()===============*/
var str = '{x:1,y:2,z:3}';
/*JSON.parse：将json字符串转为对象或者数组，能转的字符串必须：
	  属性名必须使用双引号；
	  末尾不可有多余的逗号；
	  数字不能用 0 开头，小数点后必须跟着至少一个数字。 */
var strObj = JSON.parse(JSON.stringify(str)); //str无法直接转，key名不是字符串，要先JSON.stringify
console.log(typeof(strObj)); //typeof返回对象的类型
console.log("JSON.parse： " + strObj.y);
JSON.parse('[1, 2, 3, 4 ]'); //转数组
JSON.parse('{"foo" : 1 }');  //转对象
/* JSON.stringify():将对象或数组转为json字符串 */
JSON.stringify({x: 5, y: 6});  // "{"x":5,"y":6}"
JSON.stringify([1, "false", false]);       // '[1,"false",false]'
JSON.stringify([new Number(1), new String("false"), new Boolean(false)]); // '[1,"false",false]'
JSON.stringify([undefined, Object, Symbol("")]);    // '[null,null,null]' 
JSON.stringify({x: undefined, y: Object, z: Symbol("")}); // '{}'


console.log("======================创建函数========================");
/*8、创建函数两种方式===============*/
//创建函数方式一：函数声明，定义前后都可用，add1(1+2)
function add1(a,b){
	return a + b;
}
console.log(add1(1,2));
//创建函数方式二：函数声明表达式，定义之后才可用，add2(1+2)
var add2 = function(a,b){
	return a+b;
}
console.log(add2(1,2));















