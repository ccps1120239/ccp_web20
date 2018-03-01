<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
	<style type="text/css">
		.search-div {
			width: 196px;
			position: absolute;
			display: none;
			z-index: 100;
			background: #ffffff;
			border: 1px solid #cccccc;
		}
	</style>
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript">
      function searchByWord(obj) {
          //1获取输入框的内容
          var inputVal = $(obj).val();
          //2根据输入框的内容传递到后台进行模糊查询
          var content = "";
          $.ajax({
              url: "${pageContext.request.contextPath}/searchProductServlet",
              async: true,
              type: "POST",
              data: {"inputVal": inputVal},
              dataType: "json",
              success: function (data) {//成功回调函数
                  //3将商品显示到id=showPro中
                  if (data.length > 0) {
                      for (var i = 0; i < data.length; i++) {
                          content += "<div style='padding: 5px;cursor: pointer' onclick='addInput(this)' onmouseover='overFn(this)' onmouseout='outFn(this)'>" + data[i] + "</div>";
                      }
                      $("#showPro").html(content);
                      $("#showPro").css("display", "block");
                  }
              },
              error: function () {//失败回掉函数
                  alert("服务器发生未知错误，请刷新页面重试！");
              }
          });
      }

      /**
       *
       */
      function overFn(obj) {
          $(obj).css("background", "#83A4FF");
      }

     /**
      *
      */
      function outFn(obj) {
          $(obj).css("background", "#ffffff");
      }

      /**
       * 点击添加到输入框
       * @param obj 当前点击对象
       */
      function addInput(obj) {
          var content = $("#search").val($(obj).html());
          $("#showPro").css("display", "none");
      }

	</script>
</head>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo2.png"/>
	</div>
	<div class="col-md-5">
		<img src="img/header.png"/>
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
			<li><a href="login.jsp">登录</a></li>
			<li><a href="register.jsp">注册</a></li>
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="order_list.jsp">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
				        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="product_list.htm">手机数码<span class="sr-only">(current)</span></a></li>
					<li><a href="#">电脑办公</a></li>
					<li><a href="#">电脑办公</a></li>
					<li><a href="#">电脑办公</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<%--
					position: relative
					生成相对定位的元素，相对于其正常位置进行定位。
					--%>
					<div class="form-group" style="position: relative">
						<input id="search" type="text" class="form-control" placeholder="Search" onkeyup="searchByWord(this)">
						<%--
						position: absolute：
						绝对定位：生成绝对定位的元素，相对于 static 定位以外的第一个父元素进行定位。
						--%>
						<div class="search-div" id="showPro">

						</div>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
	</nav>
</div>