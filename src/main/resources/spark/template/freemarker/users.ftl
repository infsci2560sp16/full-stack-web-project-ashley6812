<!DOCTYPE html>
<html>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Wicked Inventory</title>

  <link href="stylesheets/bootstrap.min.css" rel="stylesheet">
  <link href="stylesheets/main.css" rel="stylesheet">
  <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
  <link href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css" rel="stylesheet">


  <!-- Morris -->
  <link href="stylesheets/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

  <link href="stylesheets/animate.css" rel="stylesheet">
  <link href="stylesheets/style.css" rel="stylesheet">
    
  <!-- Javascript -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-beta1/jquery.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity   ="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  <script type="text/javascript" src="js/report-loader.js"></script>
  <script type="text/javascript" src="js/dynamic-table.js"></script>
  <script type="text/javascript" src="js/post-json.js"></script>


  <!-- jQuery UI -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/i18n/jquery-ui-i18n.js"></script>


</head>

<body>
    <div id="wrapper">
    <nav class="navbar-default navbar-static-side" >
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">Ashley Fittz</strong>
                         </span> <span class="text-muted text-xs block">Director of IT</span> </span> </a>
                         </div>
                    <div class="logo-element">
                        IN+
                    </div>
                </li>
                <li class="active">
                    

<!-- Daily Notifications for Tasks to Complete Every Week -->
<#assign aDateTime = .now>
<#assign day = aDateTime?string["EEEE"]>

<#if day == "Monday">
<div class="notification">Monday Task: Audit Network for New Devices</div>

<#elseif day == "Tuesday">
<div class="notification">Tuesday Task: Create Report for Department Managers</div>

<#elseif day == "Wednesday">
<div class="notification">Wednesday Task: Test Network Security</div>

<#elseif day == "Thursday">
<div class="notification">Thursday Task: Review Server Logs</div>

<#elseif day == "Friday">
<div class="notification">Friday Task: Buy Lunch for Support Technicians</div>

<#else>
<div class="notification">Why are you working on the weekend?!</div>

</#if>
<br>


<!-- Navigation -->
   <li>
        <a href="dashboard.html"><i class="fa fa-th-large"></i> <span class="nav-label">Dashboard</span></a>
     </li>                                    
     <li>
        <a href="all-assets"><i class="fa fa-diamond"></i> <span class="nav-label">Assets</span></a>
     </li>
     <li>
        <a href="reports"><i class="fa fa-bar-chart-o"></i>Reports<span class="nav label"></span></a>
     </li>
     <li>
        <a href="all-users"><i class="fa fa-envelope"></i>Users</a>
     </li>
   </ul>
        </div>

    </nav>

        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
        <nav class="navbar navbar-static-top white-bg" style="margin-bottom: 0">
          <ul class="nav navbar-top-links navbar-right">

                <li class="views-number">USERS</li>
                <li><a href="index.html"> <i class="fa fa-sign-out"></i>Logout</a></li>
            </ul>

        </nav>
        </div>

        <div class="wrapper wrapper-content">
        <div class="row"></div>
        <div class="row"></div>
                  
                  <div class="col-lg-12">
                    <h5>Select User from Dropdown to View Details</h5>
                  </div>
                  <div class="row"></div>
              

<!-- POST Request and Accept JSON -->                                     
              <div id="all-users"></div>
              
                <li>
                  <select class="form-control" id="Name" onchange="userNames()">
                    <option value="Ashley Fittz">Ashley Fittz</option>
                    <option value="Bruce Wayne">Bruce Wayne</option>
                    <option value="Clark Kent">Clark Kent</option>
                  </select>
                </li>                              

        </div>
        </div>
        </div>
</body>
</html>
