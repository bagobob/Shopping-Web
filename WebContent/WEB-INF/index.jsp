<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Styles -->
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/home.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<link rel="stylesheet" type="text/css" href="css/form.css">
	<link rel="stylesheet" type="text/css" href="css/profil.css">


	<!-- Load icon library -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<div class="header" id="header">
  <h1 id="h1-header">Home Page</h1>
  <p id="p-header">Welcome to the new shopping website</p>
</div>

<div class="navbar">
  <a href="index">Home</a>
  <a href="contact.jsp">Contact</a>
  <a href="about.jsp">About</a>

   <div class="search-container">
    <form action="/action_page.php">
      <input type="text" placeholder="Search.." name="search">
      <button type="submit"><i class="fa fa-search"></i></button>
    </form>
  </div>
  
 <a href="cart.jsp" class="right fa fa-cart-plus" onclick="toggle_visibility('myCart');">Cart</a> 
 <%-- Vérification de la présence d'un objet utilisateur en session --%>
                <c:if test="${!empty sessionScope.sessionClient}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                    <div class="profile">
  <img class="img-profile" src="../img/img_avatar2.png" alt="Avatar" style="width:100px">
  <h1>${sessionScope.sessionClient.name}</h1>
  <a><button>logout</button></a>
  
</div>
                </c:if>
   <div class="dropdown">
  <button class="dropbtn">Account</button>
  <div class="dropdown-content">
    <!-- Login-->
    <a href="signin"> Login</a>
 

  <!-- Register-->
    <a  href="signup"> Register</a>

  </div>
  </div>
</div>

<div class="row">
  <!--
  <div class="side-left">
    <h2>About Me</h2>
    <h5>Photo of me:</h5>
    <div class="fakeimg" style="height:200px;">Image</div>
    <p>Some text about me in culpa qui officia deserunt mollit anim..</p>
    <h3>More Text</h3>
    <p>Lorem ipsum dolor sit ame.</p>
    <div class="fakeimg" style="height:60px;">Image</div><br>
    <div class="fakeimg" style="height:60px;">Image</div><br>
    <div class="fakeimg" style="height:60px;">Image</div>
  </div>
	-->
  

  <!-- Main content-->
  <div class="main" id="main">
    <h2>Products </h2>
    <h5>Title description, Dec 7, 2017</h5>

	<!-- Products Cards  -->
<!-- First Row-->
	
	
		<div class="row">
	<c:forEach items="${ sessionScope.products }" var="mapProducts">
		  <div class="column" >
		  	<h2 style="text-align:center">Product Card</h2>
		  	<div class="card">
		  	<c:set var="image" value="${ mapProducts.value.photo}" scope="session" />
			  <img src="./img/${ image }" class="flip-hov" alt="Snow" style="width:100%">
			  <h1><c:out value="${ mapProducts.value.name }"></c:out></h1>
			  <p class="price">$<c:out value="${ mapProducts.value.price }"></c:out></p>
			  <p><c:out value="${ mapProducts.value.description }"></c:out>.</p>
			<form action="cart"> <p>  <button type="submit">Add to Cart</button></p> </form>
			</div>
		  </div>
	</c:forEach>
		   
		</div>
	
		

		<h2>Carousel </h2>
    <h5>The most selling</h5>
    


    <br>

		<h2>Counter Section</h2>
    <h5>Title description, Sep 2, 2017</h5>
     <!-- Section counter -->
	 <section id="counterSection">
	 	<div class="rowCounter">
  <div class="columnCounter">
    <div class="cardCounter">
      <p><i class="fa fa-user"></i></p>
      <h3>11+</h3>
      <p>Partners</p>
    </div>
  </div>

  <div class="columnCounter">
    <div class="cardCounter">
      <p><i class="fa fa-check"></i></p>
      <h3>55+</h3>
      <p>Projects</p>
    </div>
  </div>

  <div class="columnCounter">
    <div class="cardCounter">
      <p><i class="fa fa-smile-o"></i></p>
      <h3>100+</h3>
      <p>Happy Clients</p>
    </div>
  </div>

  <div class="columnCounter">
    <div class="cardCounter">
      <p><i class="fa fa-coffee"></i></p>
      <h3>100+</h3>
      <p>Meetings</p>
    </div>
  </div>
</div>
	 </section>
	<!-- End of counter section-->

	<!-- Beginning of Testimonials-->
	<h2>Testimonials</h2>
    <section id="testimonials">
			
		<p>This is the place for testimonials</p>
			<!-- Slideshow container -->
		<div class="slideshow-container">

		  <!-- Full-width slides/quotes -->
		  <div class="mySlides">
		    <q>I love you the more in that I believe you had liked me for my own sake and for nothing else</q>
		    <p class="author">- John Keats</p>
		  </div>

		  <div class="mySlides">
		    <q>But man is not made for defeat. A man can be destroyed but not defeated.</q>
		    <p class="author">- Ernest Hemingway</p>
		  </div>

		  <div class="mySlides">
		    <q>I have not failed. I've just found 10,000 ways that won't work.</q>
		    <p class="author">- Thomas A. Edison</p>
		  </div>

		  <!-- Next/prev buttons -->
		  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
		  <a class="next" onclick="plusSlides(1)">&#10095;</a>
		</div>

		<!-- Dots/bullets/indicators -->
		<div class="dot-container">
		  <span class="dot" onclick="currentSlide(1)"></span>
		  <span class="dot" onclick="currentSlide(2)"></span>
		  <span class="dot" onclick="currentSlide(3)"></span>
		</div>

		</section>
	<!-- End of the testimonials-->

	<!-- Beginning of Newsletter-->


		<h2>CSS Newsletter</h2>
		<section>
		<form action="/action_page.php" class="newsletter">
		  <div class="container">
		    <h2>Subscribe to our Newsletter</h2>
		    <p>Lorem ipsum text about why you should subscribe to our newsletter blabla. Lorem ipsum text about why you should subscribe to our newsletter blabla.</p>
		  </div>

		  <div class="container" style="background-color:white">
		    <input type="text" placeholder="Name" name="name" required>
		    <input type="text" placeholder="Email address" name="mail" required>
		    <label>
		      <input type="checkbox" checked="checked" name="subscribe"> Daily Newsletter
		    </label>
		  </div>

		  <div class="container">
		    <input type="submit" value="Subscribe">
		  </div>
		</form>
		</section>
	<!--  End of newsletter-->

  </div>
  <!-- End of main-->

</div>


<div class="footer">
  <div class="footer__addr">
    <h1 class="footer__logo">Something</h1>
        
    <h2>Contact</h2>
    
    <address>
      5534 Somewhere In. The World 22193-10212<br>
          
      <a class="footer__btn" href="mailto:example@gmail.com">Email Us</a>
    </address>
  </div>
  
  <ul class="footer__nav">
    <li class="nav__item">
      <h2 class="nav__title">Media</h2>

      <ul class="nav__ul">
        <li>
          <a href="#">Online</a>
        </li>

        <li>
          <a href="#">Print</a>
        </li>
            
        <li>
          <a href="#">Alternative Ads</a>
        </li>
      </ul>
    </li>
    
    <li class="nav__item nav__item--extra">
      <h2 class="nav__title">Technology</h2>
      
      <ul class="nav__ul nav__ul--extra">
        <li>
          <a href="#">Hardware Design</a>
        </li>
        
        <li>
          <a href="#">Software Design</a>
        </li>
        
        <li>
          <a href="#">Digital Signage</a>
        </li>
        
        <li>
          <a href="#">Automation</a>
        </li>
        
        <li>
          <a href="#">Artificial Intelligence</a>
        </li>
        
        <li>
          <a href="#">IoT</a>
        </li>
      </ul>
    </li>
    
    <li class="nav__item">
      <h2 class="nav__title">Legal</h2>
      
      <ul class="nav__ul">
        <li>
          <a href="#">Privacy Policy</a>
        </li>
        
        <li>
          <a href="#">Terms of Use</a>
        </li>
        
        <li>
          <a href="#">Sitemap</a>
        </li>
      </ul>
    </li>
  </ul>
  
  <div class="legal">
    <p>&copy; 2019 Something. All rights reserved.</p>
    
    <div class="legal__links">
      <span>Made with <span class="heart">♥</span> remotely from Anywhere</span>
    </div>
  </div>
</div>


<script type="text/javascript" src="js/home.js" ></script>
</body>
</html>
