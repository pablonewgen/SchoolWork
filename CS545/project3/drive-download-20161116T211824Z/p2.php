<?php
## Date validation from: https://stackoverflow.com/questions/12030810/php-date-validation

function validate_data($params) {
    $msg = "";
	$dateCheck = checkDates($params[11]);
	$age = dobCalculate($params[11]);
    if(strlen($params[0]) == 0)
        $msg .= "Please enter the first name.<br />";
	if(strlen($params[2]) == 0)
        $msg .= "Please enter the last name.<br />"; 	
    if(strlen($params[3]) == 0)
        $msg .= "Please enter the first address line.<br />"; 
    if(strlen($params[5]) == 0)
        $msg .= "Please enter the city.<br />"; 
    if(strlen($params[6]) == 0)
        $msg .= "Please select a state.<br />";
	elseif($params[6] == "0")
        $msg .= "Please select a state.<br />"; 	
    if(strlen($params[7]) == 0)
        $msg .= "Please enter the zip code.<br />";
    elseif(!is_numeric($params[7])) 
        $msg .= "Zip code may contain only numeric digits.<br />";
	if(strlen($params[8]) == 0)
        $msg .= "Please enter the phone number.<br />";
	elseif(!is_numeric(preg_replace("/[^0-9]/", '', $params[8]))) 
        $msg .= "Phone number may contain only numeric digits.<br />";
    if(strlen($params[9]) == 0)
        $msg .= "Please enter email.<br />";
    elseif(!filter_var($params[9], FILTER_VALIDATE_EMAIL))
        $msg .= "Your email appears to be invalid.<br/>";
	if(strlen($params[10]) == 0)
        $msg .= "Please select your gender.<br />";
	if(strlen($params[11]) == 0)
        $msg .= "Please enter a date of birth.<br />";
	elseif($dateCheck > 0) {
		if($dateCheck == 1){
			$msg .= "Please enter a valid date of birth. <br />";
		}
		else {
			$msg .= "Please enter a valid formatted date of birth. <br />";
		}
	}
	elseif($age < 16) {
        $msg .= "You must be at least 16 years old to sign up.<br />";
	}
	if(strlen($params[13]) == 0)
        $msg .= "Please select your experience level.<br />";
	if(strlen($params[14]) == 0)
        $msg .= "Please select your race category.<br />";
	if(strlen($params[15]) == 0) {
        $msg .= "Please upload a profile picture.<br />";
		$msg .= "$params[15]<br />";

	}	
    if($msg) {
        write_form_error_page($msg);
        exit;
        }
    }


	
function write_form_error_page($msg) {
    write_header();
	write_form();
	echo "<h4>Sorry, an error occurred<br />",
    $msg,"</h4>";
    write_footer();
    }  
    
function write_form() {
    print <<<ENDBLOCK
	<head>
		<meta charset="utf-8">
		<title>Project 3 - Marathon Form JADRAN034</title>
		<link rel="stylesheet" href="proj3css.css">
		<script src="/jquery/jquery.js"></script>
		<script src="proj3jsW.js"></script>
	</head>
	<body>
	<img class="banner" src="images/sdsu.jpg" alt="SDSU logo" />
	<h1>Active for Life Marathon</h1>
	<fieldset>
	<legend>Personal Information</legend>
        <form  
        name="personal_info"
        method="post" 
        action="process_request.php"
		enctype="multipart/form-data">
			<ul>
            	<li><h2>First Name*</h2></li>
				<li> <input type="text" name="fname" value="$_POST[fname]" placeholder = "First Name" id="fname" size="52" /></li>
				<li><h2>Middle Name</h2></li>
				<li> <input type="text" name="mname" value="$_POST[mname]" placeholder = "Middle Name" id="mname" size="52" /></li> 
				<li><h2>Last Name*</h2>	</li>			
				<li> <input type="text" name="lname" value="$_POST[lname]" placeholder = "Last Name" id="lname" size="52" /></li> 
				<li><h2>Address Line 1*</h2></li>
				<li> <input type="text" name="address1" value="$_POST[address1] "placeholder = "Address (Line 1)" id="address1" size="52" /></li>
				<li><h2>Address Line 2</h2></li>    
				<li> <input type="text" name="address2" value="$_POST[address2]" placeholder = "Address (Line 2)" id="address2" size="52" /></li>
				<li><h2>City*</h2></li>  				
				<li> <input type="text" name="city"  value="$_POST[city]" placeholder = "City " id="city" size="52" />
				<li><h2>State*</h2></li>
ENDBLOCK;
			echo "<select name = 'state'>";
			$state_values = array("0","Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","District Of Columbia","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming");
            foreach($state_values as $item) {
                echo "<option value='$item'";
                if($item == $_POST[state])
                    echo " selected";
                echo ">$item</option>";
			}
	print <<<ENDBLOCK
			</select>	
				<li><h2>Zip Code*</h2></li>				
				<li> <input type="text" name="zip" value="$_POST[zip]" placeholder = "Zip Code" id="zip" size="52" maxlength="5" /> </li>
				<li><h2>Phone Number*</h2></li>    
				<li> <input type='tel' name="phone" value="$_POST[phone]" placeholder="ex: 555-555-5555" id="phone" size="52" /> </li>
				<li><h2>Email Address*</h2></li>
				<li> <input type="text" name="email" value="$_POST[email]" placeholder = "ex: abc@abcd.com" id="email" size="52" /> </li>
ENDBLOCK;
				echo "<li><h2>Gender*</h2></li>";
				$gender_choice = array("male","female","other");
				echo "<li>";
				foreach($gender_choice as $item) {
                echo "<input type='radio' name='gender'  value='$item'";
                if($item == $_POST[gender])
                    echo " checked='checked'";
                echo " />$item";
                } 
				echo "</li>";
	print <<<ENDBLOCK
				<li><h2>Date of Birth*</h2></li>
				<li> <input type="text" name="dob" value="$_POST[dob]" placeholder="ex: MM/DD/YYYY" id="dob" size="52" maxlength="10" /> </li>
				<li><h2>Known Medical Conditions</h2></li>
				<li> <input type="text" name="medical" value="$_POST[medical]" placeholder = "Medical Conditions" id="medical" size="52" /></li>			
ENDBLOCK;
				echo "<li><h2>Experience Level*</h2></li>";
				$experience_choice = array("novice","experienced","expert");
				echo "<li>";
				foreach($experience_choice as $item) {
                echo "<input type='radio' name='experience'  value='$item'";
                if($item == $_POST[experience])
                    echo " checked='checked'";
                echo " />$item";
                }
				echo "</li>";
				echo "<li><h2>Race Category*</h2></li>";
				echo "<li>";
				$category_choice = array("teen","adult","senior");
				foreach($category_choice as $item) {
                echo "<input type='radio' name='category'  value='$item'";
                if($item == $_POST[category])
                    echo " checked='checked'";
                echo " />$item";
                } 
				echo "</li>";
	print <<<ENDBLOCK
				<li></li>
				<li>&nbsp;</li>
				<li><h2>Upload Profile Picture</h2></li>
				<li>
				<input type="file" id="picture" name="file">
				</li>				
				<li>
				<li></li>
				<div id="status">&nbsp;</div>
				</li>
			</ul>
			<div id="button_panel">  
				<input type="submit" value="Submit Form"  class="button" name="submitButton"/>
				<input type="reset" value="Clear Form" class="button" name = "clearButton"/>
			</div>              
        </form>   
	</fieldset> 
	</body>
ENDBLOCK;

}  

function process_parameters($params) {
    global $bad_chars;
    $params = array();
    $params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['fname'])));
	$params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['mname'])));
	$params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['lname'])));
    $params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['address1'])));
	$params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['address2'])));
    $params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['city'])));
    $params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['state'])));
    $params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['zip'])));
	$params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['phone'])));
    $params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['email'])));
	$params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['gender'])));
	$params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['dob'])));
	$params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['medical'])));
	$params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['experience'])));
	$params[] = htmlspecialchars(trim(str_replace($bad_chars, "",$_POST['category'])));
	$params[] = trim(str_replace($bad_chars, "",$_FILES['file']['name']));

    return $params;
    }
	
function dobCalculate($dob) {
	$bday = new DateTime($dob);
	$today = new DateTime('00:00:00');
	$diff = $today->diff($bday);
	return $diff->y;
}

function checkDates($dob) {
	$test_arr  = explode('/', $dob);
	if (count($test_arr) == 3) {
		if (checkdate($test_arr[0], $test_arr[1], $test_arr[2])) {
			return 0;
		} else {
			return 1;
		}
	} else {
		return 2;
	}
	
}

function store_data_in_db($params) {
    # get a database connection
    $db = get_db_handle();  ## method in helpers.php
    ##############################################################
    $sql = "SELECT * FROM person WHERE ".
    "fname ='$params[0]' AND ".
	"mname ='$params[1]' AND ".
	"lname ='$params[2]' AND ".
    "address1 = '$params[3]' AND ".
	"address2 ='$params[4]' AND ".
    "city = '$params[5]' AND ".
    "state = '$params[6]' AND ".
    "zip = '$params[7]' AND ".
	"phone ='$params[8]' AND ".
	"email ='$params[9]' AND ".
	"gender ='$params[10]' AND ".
	"dob ='$params[11]' AND ".
	"medical ='$params[12]' AND ".
	"experience='$params[13]' AND ".
    "category='$params[14]' AND ".
	"filename ='$params[15]';";
##echo "The SQL statement is ",$sql;    
    $result = mysqli_query($db, $sql);
    if(mysqli_num_rows($result) > 0) {
        write_form_error_page('This record appears to be a duplicate');
        exit;
        }
##OK, duplicate check passed, now insert
    $sql1 = "INSERT INTO person(fname,mname,lname,address1,address2,city,state,zip,phone,email,gender,dob,medical,experience,category,filename) ".
    "VALUES('$params[0]','$params[1]','$params[2]','$params[3]','$params[4]','$params[5]','$params[6]','$params[7]','$params[8]','$params[9]','$params[10]','$params[11]','$params[12]','$params[13]','$params[14]','$params[15]');";
##echo "The SQL statement is ",$sql;    
    mysqli_query($db,$sql1);
    close_connector($db);
    }
        
	
?>