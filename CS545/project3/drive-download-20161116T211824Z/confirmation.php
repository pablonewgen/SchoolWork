<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;
    charset=iso-8859-1" />
    <title>SDSU: Active For Life Sign-Up Confirmation </title>
<link rel="stylesheet" type="text/css" href="style.css" />
    

</head>


<body>
<img class="banner" src="images/sdsu.jpg" alt="SDSU logo" />
<h1>Active for Life Marathon</h1>
<?php
$UPLOAD_DIR = '_apics_';
$COMPUTER_DIR = '/home/jadrn034/public_html/proj3/_apics_';
echo <<<ENDBLOCK
    <h1>$params[0] $params[2], thank you for registering.</h1>
	<h1><img src="$UPLOAD_DIR/$params[15]" width='400px' /></h1>
	<fieldset>
    <table>
		<tr>
		</tr>
		<tr>
			<td>Name</td>
			<td>$params[0] $params[2]</td>
		</tr>
        <tr>
            <td>Address line 1</td>
            <td>$params[3]</td>
        </tr>
		<tr>
            <td>Address</td>
            <td>$params[4]</td>
        </tr>
        <tr>
            <td>City</td>
            <td>$params[5]</td>
        </tr>
        <tr>
            <td>State</td>
            <td>$params[6]</td>
        </tr>
        <tr>
            <td>Zip Code</td>
            <td>$params[7]</td>
        </tr>
        <tr>
            <td>Phone</td>
            <td>$params[8]</td>
        </tr>
		<tr>
            <td>Email</td>
            <td>$params[9]</td>
        </tr>
		<tr>
            <td>Gender</td>
            <td>$params[10]</td>
		</tr>
		<tr>
            <td>Date of Birth</td>
            <td>$params[11]</td>
        </tr>
		<tr>
            <td>Medical History </td>
            <td>$params[12]</td>
        </tr>
		<tr>
            <td>Experience Level</td>
            <td>$params[13]</td>
        </tr>
		<tr>
            <td>Race Category</td>
            <td>$params[14]</td>
        </tr>
		<tr>
            <td>Race TEST</td>
            <td>$params[15]</td>
        </tr>		
    </table>
	</fieldset>
ENDBLOCK;

?>
</body></html>