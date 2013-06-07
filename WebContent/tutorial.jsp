<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!--  insert head  -->
<%@include file="head.jsp"%>
<%@include file="WEB-INF/loggedHeader.jsp"%>
<body onload="Carousel()">
	<div id="wrapper">
		<section>
		<div id="Carousel" style="position: relative">
			<img src="img/placeholder.gif" width="540" height="540" />
		</div>
		
		 <div id="tutorial">
                <h1 id="howWorks" class="span12 btn btn-primary">Как работи платформата</h1>
                <div id="whoAsks" class="span3 btn btn-info ">
                    <h3 class="headTutorial">Кой задава въпроси?</h3>
                    <p >
                        Въпрос може да бъде зададен от всеки регистриран потребител в момента. 
                    По принцип идеята е въпросите да бъдат задавани от ръководители на дадена компания,
                    с цел да получи някаква прогноза относно въпросa.
                    </p>
                </div>
                 <div id="howTrade" class="span3 btn btn-info ">
                    <h3 class="headTutorial">Как се търгува?</h3>
                    <ul>
                        <li>Залагаш на твоите фаворите, че ще спечелят</li>
                        <li>Залагаш на екипите с най-малки шансове да спечелят, че ще загубят.</li>
                        <li>Правиш къса позиция, като продаваш акции, които нямаш.</li>
                    </ul>
                </div>
                <div id="howBuy" class="span4 btn btn-info ">
                    <h3 class="headTutorial">Как сe купува?</h3>
                    <p>Ако си купил 10 акции от компания Х на цена 60 cointels за акция, ще си заложил 600 cointels.</p>
                    <ul>
                        <li>Aко компания Х спечели, твоите 10 акции ще бъдат преоценени при цена 100 cointels и ще имаш 10х(100-60)=400 cointels печалба или общо 1000 cointels.</li>
                        <li>Aко компания Х загуби, твоите 10 акции ще бъдат преоценени при цена 0 cointels и ще имаш 10х(0-60)=600 cointels загуба или общо 0 cointels.</li>
                    </ul>
                </div>
                <div id="howSell" class="span4 btn btn-info ">
                    <h3 class="headTutorial">Как сe продава?</h3>
                    <p>Ако си направил 10 къси позиции(продал си 10 акции на цена 60 cointels, без да ги имаш) от компания Х, програмата ще ти блокира максималната загуба от 400 cointels. </p>
                    <ul>
                        <li>Ако компания Х спечели, твоите 10 акции ще бъдат преоценени на цена 100 cointels и ще имаш 10х(60-100)= 400 cointels загуба. Защо се случва това? Защото когато се обяват победителите, системата автоматично преоценява акциите на реалната им цена и ги откупува. </li>
                        <li>Ако компанията Х загуби, твоите 10 акции ще бъдат преоценени на цена 0 cointels и ще имаш 10х(60-0)=600 cointels печалба.</li>
                    </ul>
                </div>
            </div>
		<script type="text/javascript">
			/***********************************************
			 * Carousel Slideshow script- © Ger Versluis 2003
			 * Permission granted to DynamicDrive.com to feature script
			 * This notice must stay intact for legal use
			 * Visit http://www.dynamicdrive.com/ for full source code
			 ***********************************************/

			/********************************************************
			    Create a div with transparent place holder in your html	
			    <div id="Carousel" style="position:relative">
			        <img src="placeholder.gif" width="404" height="202">
			    </div>
			    placeholder width:
			         4 sided: 1.42 * carousel image width + 3
			         6 sided: 2 * carousel image width +4
			         8 sided: 2.62 * carousel image width + 5
			        12 sided: 3.87 * carousel image width + 7
			    placeholder height: 
			        carousel image height+2
			
			    Insert onload in body tag
			        <body onload="Carousel()">	
			 *********************************************************/

			// 7 variables to control behavior
			var Car_Image_Width = 448;
			var Car_Image_Height = 432;
			var Car_Border = true; // true or false
			var Car_Border_Color = "white";
			var Car_Speed = 4;
			var Car_Direction = true; // true or false
			var Car_NoOfSides = 8; // must be 4, 6, 8 or 12

			/* array to specify images and optional links. 
			    For 4 sided carousel specify at least 2 images
			    For 6 sided carousel specify at least 3
			    For 8 sided carousel specify at least 4
			    For 12 sided carousel specify at least 6
			 If Link is not needed keep it ""
			 */
			Car_Image_Sources = new Array("img/tut-ask.jpg",
					"tutorial.html#tutorial", "img/tut-all.png",
					"tutorial.html#tutorial", "img/tut-trade.jpg",
					"tutorial.html#tutorial", "img/tut-tradeNo.jpg",
					"tutorial.html#tutorial" // NOTE No comma after last line
			);

			/***************** DO NOT EDIT BELOW **********************************/
			CW_I = new Array(Car_NoOfSides / 2 + 1);
			C_ClcW = new Array(Car_NoOfSides / 2);
			C_Coef = new Array(3 * Math.PI / 2, 0, 3 * Math.PI / 2,
					11 * Math.PI / 6, Math.PI / 6, 3 * Math.PI / 2,
					7 * Math.PI / 4, 0, Math.PI / 4, 3 * Math.PI / 2,
					5 * Math.PI / 3, 11 * Math.PI / 6, 0, Math.PI / 6,
					Math.PI / 3);
			var C_CoefOf = Car_NoOfSides == 4 ? 0 : Car_NoOfSides == 6 ? 2
					: Car_NoOfSides == 8 ? 5 : 9;
			C_Pre_Img = new Array(Car_Image_Sources.length);
			var C_Angle = Car_Direction ? Math.PI / (Car_NoOfSides / 2) : 0, C_CrImg = Car_NoOfSides, C_MaxW, C_TotalW, C_Stppd = false, i, C_LeftOffset, C_HalfNo = Car_NoOfSides / 2;

			function Carousel() {
				if (document.getElementById) {
					for (i = 0; i < Car_Image_Sources.length; i += 2) {
						C_Pre_Img[i] = new Image();
						C_Pre_Img[i].src = Car_Image_Sources[i]
					}
					C_MaxW = Car_Image_Width
							/ Math.sin(Math.PI / Car_NoOfSides) + C_HalfNo + 1;
					Car_Div = document.getElementById("Carousel");
					for (i = 0; i < C_HalfNo; i++) {
						CW_I[i] = document.createElement("img");
						Car_Div.appendChild(CW_I[i]);
						CW_I[i].style.position = "absolute";
						CW_I[i].style.top = 0 + "px";
						CW_I[i].style.height = Car_Image_Height + "px";
						if (Car_Border) {
							CW_I[i].style.borderStyle = "solid";
							CW_I[i].style.borderWidth = 1 + "px";
							CW_I[i].style.borderColor = Car_Border_Color
						}
						CW_I[i].src = Car_Image_Sources[2 * i];
						CW_I[i].lnk = Car_Image_Sources[2 * i + 1];
						CW_I[i].onclick = C_LdLnk;
						CW_I[i].onmouseover = C_Stp;
						CW_I[i].onmouseout = C_Rstrt
					}
					CarImages()
				}
			}

			function CarImages() {
				if (!C_Stppd) {
					C_TotalW = 0;
					for (i = 0; i < C_HalfNo; i++) {
						C_ClcW[i] = Math.round(Math.cos(Math
								.abs(C_Coef[C_CoefOf + i] + C_Angle))
								* Car_Image_Width);
						C_TotalW += C_ClcW[i]
					}
					C_LeftOffset = (C_MaxW - C_TotalW) / 2;
					for (i = 0; i < C_HalfNo; i++) {
						CW_I[i].style.left = C_LeftOffset + "px";
						CW_I[i].style.width = C_ClcW[i] + "px";
						C_LeftOffset += C_ClcW[i]
					}
					C_Angle += Car_Speed / 720 * Math.PI
							* (Car_Direction ? -1 : 1);
					if ((Car_Direction && C_Angle <= 0)
							|| (!Car_Direction && C_Angle >= Math.PI / C_HalfNo)) {
						if (C_CrImg == Car_Image_Sources.length)
							C_CrImg = 0;
						if (Car_Direction) {
							CW_I[C_HalfNo] = CW_I[0];
							for (i = 0; i < C_HalfNo; i++)
								CW_I[i] = CW_I[i + 1];
							CW_I[C_HalfNo - 1].src = Car_Image_Sources[C_CrImg];
							CW_I[C_HalfNo - 1].lnk = Car_Image_Sources[C_CrImg + 1]
						} else {
							for (i = C_HalfNo; i > 0; i--)
								CW_I[i] = CW_I[i - 1];
							CW_I[0] = CW_I[C_HalfNo];
							CW_I[0].src = Car_Image_Sources[C_CrImg];
							CW_I[0].lnk = Car_Image_Sources[C_CrImg + 1]
						}
						C_Angle = Car_Direction ? Math.PI / C_HalfNo : 0;
						C_CrImg += 2
					}
				}
				setTimeout("CarImages()", 100)
			}

			function C_LdLnk() {
				if (this.lnk)
					window.location.href = this.lnk
			}
			function C_Stp() {
				this.style.cursor = this.lnk ? "pointer" : "default";
				C_Stppd = true;
			}
			function C_Rstrt() {
				C_Stppd = false
			}
		</script> </section>
		<%@include file="WEB-INF/foot.jsp"%>
		<script src="scripts/profileScripts.js"></script>
	</div>

</body>
</html>

