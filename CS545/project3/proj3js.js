/*
	Paul Truong Nguyen
	Project2
	JADRAN034
	Note: Leap year validation pattern found at: https://stackoverflow.com/questions/8647893/regular-expression-leap-years-and-more
*/


function validateForm() {
    var fnameP = document.forms["personal_info"]["fname"].value;
		var namePattern = /^[a-zA-Z]*$/;
	var lname = document.forms["personal_info"]["lname"].value;
	var address1 = document.forms["personal_info"]["address1"].value;
		var addressPattern = /^[0-9a-zA-Z\s]+$/;
	var city = document.forms["personal_info"]["city"].value;
	var state = document.getElementById("state");
		var stateSelIndex = state.options[state.selectedIndex].value;
	var zipcode = document.forms["personal_info"]["zip"].value;
		var zipPattern = /(^\d{5}$)/;
    var phone = document.forms["personal_info"]["phone"].value;
		var phonePattern = /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/;
	var email = document.forms["personal_info"]["email"].value;
		var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/; 
	var gender = document.getElementById("gender");
		var genderSelIndex = gender.options[gender.selectedIndex].value;
	var dob = document.forms["personal_info"]["dob"].value;
		var dobPattern = pattern = 
		"((0[13578]|1[02])[\/.](0[1-9]|[12][0-9]|3[01])[\/.](19|20)[0-9]{2})|((0[469]|11)[\/.](0[1-9]|[12][0-9]|30)[\/.](19|20)[0-9]{2})|((02)[\/.](0[1-9]|1[0-9]|2[0-8])[\/.](19|20)[0-9]{2})|((02)[\/.]29[\/.](((19|20)(04|08|[2468][048]|[13579][26]))|2000))"
	var experience = document.getElementById("experience");
		var expSelIndex = experience.options[experience.selectedIndex].value;
	var category = document.getElementById("category");
		var categorySelIndex = category.options[category.selectedIndex].value;
		
    if (fnameP == null || fnameP == "" || !fnameP.match(namePattern)) {
        document.getElementById('fname').placeholder = "First name must be filled out *";
        document.getElementById('fname').style.borderColor = "red";
        document.personal_info.fname.focus();
        return false;
    }
    else {
    	document.getElementById('fname').style.borderColor = "green";
    }
	
	if (lname == null || lname == "" || !lname.match(namePattern)) {
        document.getElementById('lname').placeholder = "Last name must be filled out * ";
        document.getElementById('lname').style.borderColor = "red";
        document.personal_info.lname.focus();
        return false;
    }
    else {
    	document.getElementById('lname').style.borderColor = "green";
    }
	
	if (address1 == null || address1 == "" || !address1.match(addressPattern)) {
        document.getElementById('address1').placeholder = "A mailing address is missing *"
        document.getElementById('address1').style.borderColor = "red";
        document.personal_info.address1.focus();
        return false;
    }
    else {
    	document.getElementById('address1').style.borderColor = "green";
    }
	
	if (city == null || city == "" || !city.match(namePattern)) {
        document.getElementById('city').placeholder = "A city must be filled out with the mailing address.";
        document.getElementById('city').style.borderColor = "red";
        document.personal_info.city.focus();
        return false;
    }
    else {
    	document.getElementById('city').style.borderColor = "green";
    }
	
	if (stateSelIndex == 0) {
		document.getElementById('state').style.borderColor = "red";
        document.personal_info.state.focus();
		return false;	
    }
    else {
    	document.getElementById('state').style.borderColor = "green";
    }
	
	if (!zipcode.match(zipPattern)) {
		document.getElementById('zip').placeholder = "Please enter a correct zip code * ex: 55555";
		document.getElementById('zip').style.borderColor = "red";
        document.personal_info.zip.focus();
		return false;
	}
	else {
    	document.getElementById('zip').style.borderColor = "green";
    }
	
	if (!phone.match(phonePattern)) {
		document.getElementById('phone').placeholder = "Phone number required * ex: 555-555-5555";
		document.getElementById('phone').style.borderColor = "red";
        document.personal_info.phone.focus();
		return false;
	}
	else {
    	document.getElementById('phone').style.borderColor = "green";
    }
    
	if (!email.match(emailFormat)) {
		document.getElementById('email').placeholder = "An email is required * ex: abcd@xyz.com";;
		document.getElementById('email').style.borderColor = "red";
        document.personal_info.email.focus();

		return false;
	}
	else {
    	document.getElementById('email').style.borderColor = "green";
    }
	
	if (genderSelIndex == 0) {
		document.getElementById('gender').style.borderColor = "red";
        document.personal_info.gender.focus();
		return false;	
    }
    else {
    	document.getElementById('gender').style.borderColor = "green";
    }
	
	if (!dob.match(dobPattern)) {
		document.getElementById('dob').style.borderColor = "red";
        document.personal_info.dob.focus();
		return false;
	}
	else {
    	document.getElementById('dob').style.borderColor = "green";
    }
	
	if (expSelIndex == 0) {
		document.getElementById('experience').style.borderColor = "red";
        document.personal_info.experience.focus();
		return false;	
    }
    else {
    	document.getElementById('experience').style.borderColor = "green";
    }
	
	if (categorySelIndex == 0) {
		document.getElementById('category').style.borderColor = "red";
        document.personal_info.category.focus();
		return false;	
    }
    else {
    	document.getElementById('category').style.borderColor = "green";
    }
	
	alert("Validations passed and completed");
	return true;
}