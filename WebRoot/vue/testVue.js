/* 实例化Vue：
 * 	el:				element,需要获取的元素，是html的根容器元素
 * 	data:			存储的数据，在页面直接展示 {{ ... }}
 *	methods:		存储各种方法，可传参
 *	data-binding:	给属性绑定对应的值。
 *		v-bind
 *		v-html
 *		v-on
 */
var vueOne = new Vue({
	el:"#vue-app-one",	//id为vue-app的元素是根容器，作用域
	data:{
		name: 'Tonny',
		age:30,
		message: 'Hello Vue.js!',
		website:'http://baidu.com',
		websiteTag:'<a href="http://baidu.com" >websiteTag</a>',
		x:0,
		y:0,
		a:0,
		b:0,
		changeColor:false,
		changeLength:false,
		error:false,
		success:false,
		hobbies:["read","play","movie"],
		users:[{name:"Tom",age:14},{name:"Kobe",age:29}]
	},
	methods:{
		agreet:function(time){
			return 'Good ' + time + ', ' + this.name + '!';
		},
		add:function(){
			this.age++;
		},
		substract:function(des){
			this.age -= des;
		},
		updateXY:function(event){
			//console.log(event);//在浏览器的控制台中可以看到鼠标的移动信息，可以看到事件的属性值信息
			//this.x=event.clientX;
			//this.y=event.clientY;
			this.x=event.offsetX;
			this.y=event.offsetY;
		},
		logName:function(){
			console.log("您正在输入Name...");
			this.name = this.$refs.refName.value;
		},
		logAge:function(){
			console.log("您正在输入Age...");
		}
	},
	computed:{
		addToA:function(){
			return this.a + this.age;
		},
		addToB:function(){
			return this.b + this.age;
		},
		comClasses:function(){
			return {
				bindDiv:true,
				changeColor:this.changeColor,
				changeLength:this.changeLength
			}
		}
	}
});

vueOne.message = "message will change by vueTwo";

var vueTwo = new Vue({
	el:"#vue-app-two",
	data:{
		
	},
	methods:{
		changeTitle:function(){
			vueOne.message = "message of vueOne has changed!";  //调用第一个对象的方法
		}
	},
	computed:{
		
	}
});

//vue组件，vue实例：其他的属性都在组件中实现，只保留el元素
var greetingData = {
	name:"鹿晗"
}
Vue.component("greeting",{
	template:	//模板里面只能有一个根标签。`：ES6语法，里面可以换行
		`<p>
			{{name}}：大家好，给大家介绍一下我的女朋友@关晓彤
			<button v-on:click="changeName">改名</button>
		</p>`,
	data:function(){
		/*return greetingData;*/
		return {
			name:"鹿晗"
		}
	},
	methods:{
		changeName:function(){
			this.name = "Herry";
		}
	}
	
})
new Vue({
	el:"#vue-app-three"
});
new Vue({
	el:"#vue-app-four"
});
