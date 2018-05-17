<html>
<meta charset="utf-8">
<script src="http://www.w3school.com.cn/jquery/jquery-1.11.1.min.js"> </script>
<body>

<h2>Hello World!!!</h2>
<button onclick="test()">请求</button>
</body>
<script>
	function user(name,password) {
		var o = {};
		o.name = name;
		o.password = password;
		return o;
	}
	function test() {
		var user1 = user("吴丛明","123456");
		console.log(JSON.stringify(user1))
		$.ajax({
			type:"POST",
			url:"/user/login",
			data:JSON.stringify(user1),
			success:function(result) {
				console.log(result)
			}
			
		})
	}
	var ws = null;
	function connect() {
		if('WebSocket' in window)
			ws = new WebSocket("ws://localhost:8081/socketServer/123");
		else if('MozWebSocket' in window)
			ws = new WebSocket('ws://localhost:8081/socketServer/123');
		ws.onmessage = function(e) {
			alert(e.data)
		};
		ws.onclose = function(e) {
			alert("0")
		};
		ws.onopen = function(e) {
			alert("1")
			ws.send("测试")
		}
	}
	connect();
	
</script>
</html>
