<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Name Game</title>
<script src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
	<script type="text/javascript">
		var tries = 5;

		$(function() {
			$("input[name='play']").click(function() {
				$(".no-radio-selection-error").css("visibility", "hidden");
			});
		});

		function checkResponse(id, isName) {
			if (tries > 0) {
				$
						.ajax({
							url : "/check-response/" + id,
							success : function(result) {
								$(".result").html("");
								$(".attempts").html("");
								if (result == true) {
									$(".result")
											.append(
													"<h4 style='color:green;'>SUCCESS!!</h4>");
									$(".img-profile")
											.css("filter", "blur(5px)");
									$(".line").css("text-decoration","line-through");
									tries = -1;
								} else {
									tries -= 1;
									var attempts = "<h4 style='font-style:italic;'>"
											+ tries
											+ " attempts remaining!</h4>";
									if (tries == 0) {
										$(".result").append("<h4>REPLAY!</h4>");
										$(".img-profile").css("filter",
												"blur(5px)");
										$(".line").css("text-decoration","line-through");
									} else {
										$("#"+id).css("text-decoration","line-through");
										$(".result")
												.append(
														"<h4 style='color:red;'>FAILURE!TRY AGAIN!!</h4>");
									}
									$(".attempts").append(attempts);
								}
								$(".btn-restart").attr("disabled", false);
							}
						});
			}
		}

		function getProfiles(isStart) {
			$(".btn-start").attr("disabled", true);
			$(".btn-restart").attr("disabled", false);
			var straightVal = $("input[value='straight']:checked").val();
			var reverseVal = $("input[value='reverse']:checked").val();

			if (straightVal) {
				$(".straight-rules").show();
				$(".reverse-rules").css("display", "none");
			} else {
				$(".reverse-rules").show();
				$(".straight-rules").css("display", "none");
			}
			if (!isStart) {
				$(".profiles").html("");
				$(".name").html("");
				$(".names").html("");
				$(".result").html("");
				$(".attempts").html("");
				tries = 5;
			}
			$
					.ajax({
						url : "/profiles",
						success : function(result) {
							var personList = result;
							for (var i = 0; i < personList.length; i++) {
								if (straightVal) {
									var html = "<a class='img-link' onclick='checkResponse(&quot;"
											+ personList[i].id
											+ "&quot;,false)'><img class='img-profile img-thumbnail'"
						+ "src='"+ personList[i].headshot.url
						+ "' border='2' alt='" + personList[i].headshot.alt + "' style='width: 200px; "
						+ "height: 200px; margin-top:20px;'></a>";
									$(".profiles").append(html);
								} else {
									var html = "<a style='color:inherit;' href='javascript:checkResponse(&quot;"+personList[i].id
											+"&quot;,true)'><p id='"+personList[i].id+"' class='line'><b>"
											+ personList[i].firstName
											+ " "
											+ personList[i].lastName
											+ "</b></p></a>";
									$(".names").append(html);
								}
							}
							if (straightVal) {
								$
										.ajax({
											type : "get",
											url : "/select-person-name",
											success : function(name) {
												console.log(name);
												var name = "<h5 style='font-size:30px';>"
														+ name + "</h1>";
												$(".name").append(name);
											}
										});
							} else {
								$
								.ajax({
									type : "get",
									url : "/select-person",
									success : function(data) {
										console.log(data);
										var html = "<img class='img-thumbnail' src='"+data.headshot.url
										+"' alt='"+data.headshot.alt+"' style='width:200px;height:200px;'>"
										$(".names").append(html);
									}
								});
							}
						}
					});
		}
	</script>
	<div class="container" align="center">
		<h2>Name Game</h2>
		<span> <input type="radio" name="play" value="straight">Straight
			<input type="radio" name="play" value="reverse">Reverse
		</span>
		<h5>Click to Start</h5>
	</div>
	<div class="container" align="center">
		<a class="btn btn-default btn-start"
			href="javascript:getProfiles(true)">Start</a> <a
			class="btn btn-default btn-restart"
			href="javascript:getProfiles(false)" disabled=true>Restart</a>
	</div>
	<div class="no-radio-selection-error" align="center">
		<h5 style="color: red;">Select atleast one mode of play!</h5>
	</div>
	<div class="straight-rules" style="display: none;">
		<h5 style="margin-left: 10px;">Rules:</h5>
		<ul>
			<li>Click on image that matches the name displayed below</li>
			<li>You have 5 attempts</li>
			<li>You can restart at any time</li>
		</ul>
	</div>

	<div class="reverse-rules" style="display: none;">
		<h5 style="margin-left: 10px;">Rules:</h5>
		<ul>
			<li>Click on name that matches the displayed image below</li>
			<li>You have 5 attempts</li>
			<li>You can restart at any time</li>
		</ul>
	</div>
	<div class="profiles" align="center"></div>
		<div class="names" align="center"></div>
		<div class="name" align="center"></div>
		<div class="result" align="center"></div>
		<div class="attempts" align="center"></div>
</body>
</html>