<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Sign Up</title>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Styles -->
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/signup.css">
  <link rel="stylesheet" type="text/css" href="css/footer.css">


	<!-- Load icon library -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<div class="header">
  <h1>My Website</h1>
  <p>A website created by me.</p>
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
  <div class="side">
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
  
  <div class="main">
    <h2>TITLE HEADING</h2>
    <h5>Title description, Dec 7, 2017</h5>

    <form method="post" action="signup"  >
      <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="name"><b>Name</b></label>
        <!--<input type="text" placeholder="Enter Email" name="email" id="email" required> -->
        <input type="text" id="name" name="name" placeholder="Name" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Name'" required>
		<span class="erreur">${form.erreurs['name'] }</span>

        <label for="firstname"><b>FirstName</b></label>
      <!--   <input type="password" placeholder="Enter Password" name="psw" id="psw" required>  -->
        <input type="text" id="firstName" name="firstName" placeholder="FirstName" onfocus="this.placeholder = ''" onblur="this.placeholder = 'FirstName'" required>
		<span class="erreur">${form.erreurs['firstName'] }</span>

        <label for="street"><b>Street</b></label>
        <input type="text"  id="street" name="street" placeholder="Street" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Street'" required>
		<span class="erreur">${form.erreurs['street'] }</span>
      <!--    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required> -->
      
      <label for="town"><b>Town</b></label>
      <input type="text"  id="town" name="town" placeholder="Town" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Town'" required>
	  <span class="erreur">${form.erreurs['town'] }</span>
      
      <label for="postal"><b>Postal Code</b></label>
      <input type="text"  id="postal" name="postal" placeholder="Postal Code" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Postal Code'" required>
	  <span class="erreur">${form.erreurs['postal'] }</span>
      
      <label for="phone"><b>Phone Number</b></label>
      <input type="text"  id="num_tel" name="num_tel" placeholder="Phone Number " onfocus="this.placeholder = ''" onblur="this.placeholder = 'Phone Number'" required>
	  <span class="erreur">${form.erreurs['num_tel'] }</span>
	  
      
      <label for="email"><b>Email</b></label>
      <input type="text"  id="email" name="email" placeholder="Email Address" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email Address'" required>
	  <span class="erreur">${form.erreurs['email'] }</span> <br>
      
      <label for="psw"><b>Password</b></label>
      <input type="password"  id="password" name="password" placeholder="Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'" required>
	  <span class="erreur">${form.erreurs['password'] }</span>
      
      <label for="repeat-psw"><b>Confirm Password</b></label>
      <input type="password"  id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Confirm Password'" required>
      
        <hr>

        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <button type="submit" class="registerbtn">Register</button>
      </div>

      <div class="container signin">
        <p>Already have an account? <a href="signin">Sign in</a>.</p>
      </div>
    </form>

  </div>
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

</body>
</html>
