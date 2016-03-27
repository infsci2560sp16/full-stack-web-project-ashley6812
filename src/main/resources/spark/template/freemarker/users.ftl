<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Wicked Inventory</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css" rel="stylesheet">


    <!-- Morris -->
    <link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    
    <!-- Javascript -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>



    <script type="text/javascript" src="js/report-loader.js"></script>
	<script type="text/javascript" src="js/dynamic-table.js"></script>


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


                    <li>
                    <a href="dashboard.html"><i class="fa fa-th-large"></i> <span class="nav-label">Dashboard</span></a>
                </li>
                    
                   
                <li>
                    <a href="assets"><i class="fa fa-diamond"></i> <span class="nav-label">Assets</span></a>
                </li>
                <li>
                    <a href="reports.html"><i class="fa fa-bar-chart-o"></i>Reports<span class="nav label"></span></a>
                </li>
                <li>
                    <a href="users"><i class="fa fa-envelope"></i>Users</a>
                </li>
            </ul>

        </div>
    </nav>

        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
        <nav class="navbar navbar-static-top white-bg" style="margin-bottom: 0">
          <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
              <ul class="dropdown-menu dropdown-alerts">
                  <li>
                        <a href="mailbox.html">

                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="profile.html">

                        </a>
                    </li>
                    <li class="divider"></li>

                    <li class="divider"></li>
                    <li>

                    </li>
                </ul>
              </li>


                <li class="views-number">USERS</li>
                <li><a href="index.html"> <i class="fa fa-sign-out"></i>Logout</a></li>
            </ul>

        </nav>
        </div>


        <div class="wrapper wrapper-content">
        <div class="row"></div>
        <div class="row"></div>

        <div class="row">
          <div class="col-lg-12">
            <div class="ibox float-e-margins">
              <div class="ibox-title">
                <div class="ibox-tools">
                  <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                      <div class="ibox-title">
                        <h5>USERS</h5>
                      </div>
                      <div class="ibox-content">
                        <div class="row"></div>
                        <div class="table-responsive">
                        
                        
                        
    <table id="inventory" class="display" aria-describedby="tbldesc">
      <thead>
        <tr><th>Name</th><th>Role</th><th>Email</th>
      </thead>
      <tbody>
        <#list results?chunk(6) as row>
        <tr>
          <#list row as cell>
            <td>${cell}</td>
          </#list>
        </tr>
        </#list>
      </tbody>
    </table>
    
    
    <!-- Make table Dynamic using DataTables -->
    <script>
    $(document).ready(function() {
    $('#inventory').DataTable()
    });
  </script>







                        
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
       </div>
      </div>
     </div>

<!-- Mainly scripts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-beta1/jquery.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity		="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<!-- jQuery UI -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/i18n/jquery-ui-i18n.js"></script>

</body>
</html>