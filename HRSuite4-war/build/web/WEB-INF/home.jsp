<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>HR Suite</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="robots" content="all,follow">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="resources/vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="resources/vendor/font-awesome/css/font-awesome.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="resources/css/fontastic.css">
    <!-- Google fonts - Poppins -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="resources/css/style.blue.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="resources/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    <!-- Data Tables -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">

  </head>
  <body>

    <c:set var="displayPage" value="${displayPage}"/>
    <c:set var="role" value="${sBean.role}"/>
    <c:set var="chartLabels" value="${jobList}"/>
    <c:set var="chartMin" value="${minSalaryList}"/>
    <c:set var="chartMax" value="${maxSalaryList}"/>

    <c:set var="hireYears" value="${hireYears}"/>
    <c:set var="hireCounts" value="${hireCounts}"/>
    
    <c:set var="salaryChange" value="${salaryChange}"/>

    <div class="page">
      <!-- Main Navbar-->
      <header class="header">
        <nav class="navbar">
          <div class="container-fluid">
            <div class="navbar-holder d-flex align-items-center justify-content-between">
              <!-- Navbar Header-->
              <div class="navbar-header">
                <!-- Navbar Brand --><a href="home?displayPage=1" class="navbar-brand">
                  <div class="brand-text brand-big"><span>Human Resource </span><strong>Suite</strong></div>
                  <div class="brand-text brand-small"><strong>HR Suite</strong></div></a>
                <!-- Toggle Button--><a id="toggle-btn" href="#" class="menu-btn active"><span></span><span></span><span></span></a>
              </div>
              <!-- Navbar Menu -->
              <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">


                <!-- Logout    -->
                <li class="nav-item"><a href="home?displayPage=10" class="nav-link logout">Logout<i class="fa fa-sign-out"></i></a></li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
      <div class="page-content d-flex align-items-stretch"> 
        <!-- Side Navbar -->
        <nav class="side-navbar">
          <!-- Sidebar Header-->
          <div class="sidebar-header d-flex align-items-center">
            <div class="avatar"><img src="resources/img/avatar-5.jpg" alt="..." class="img-fluid rounded-circle"></div>
            <div class="title">
              <h1 class="h4">
                <c:out value="${sBean.userName}" />
                <p>
                  <c:out value="${sBean.roleName}" />
                </p>
            </div>
          </div>
          <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
          <ul class="list-unstyled">
            <li <c:if test = "${displayPage == 1}">class="active"</c:if>><a href="home?displayPage=1"> <i class="icon-home"></i>Home </a></li>
              <c:if test = "${role == 2}">
              <li <c:if test = "${displayPage == 2}">class="active"</c:if>><a href="home?displayPage=2"> <i class="fa fa-users"></i>Employees </a></li>
              </c:if>
            <!--<li><a href="charts.html"> <i class="fa fa-bar-chart"></i>Charts </a></li>-->
            <li><a href="#employeeDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-padnote"></i>Edit Employees</a>
              <ul id="employeeDropdown" class="collapse">
                <li <c:if test = "${displayPage == 3}">class="active"</c:if>><a href="home?displayPage=3"><i class="fa fa-usd"></i>   Change Salary</a></li>
                  <c:if test = "${role == 2}">
                  <li <c:if test = "${displayPage == 4}">class="active"</c:if>><a href="home?displayPage=4"><i class="fa fa-user-plus"></i>New Employee</a></li>  
                  </c:if>
              </ul>
            </li>
            <!--<li><a href="login.html"> <i class="icon-interface-windows"></i>Login page </a></li>-->
          </ul>
          <ul class="list-unstyled">
            <li> </li>
            <li> </li>
            <li> </li>
            <li> </li>
          </ul>
        </nav>
        <div class="content-inner">

          <c:if test = "${displayPage == 1}">
            <!-- Dashboard Header Section    -->
            <!-- Dashboard Counts Section-->
            <section class="dashboard-counts no-padding-bottom">
              <div class="container-fluid">
                <div class="row bg-white has-shadow">
                  <!-- Item -->
                  <div class="col-xl-4 col-sm-6">
                    <div class="item d-flex align-items-center">
                      <div class="icon bg-violet"><i class="icon-user"></i></div>
                      <div class="title"><span>No. of<br>Employees</span>
                      </div>
                      <div class="number"><strong id="emp-count"></strong></div>
                    </div>
                  </div>
                  <!-- Item -->
                  <div class="col-xl-4 col-sm-6">
                    <div class="item d-flex align-items-center">
                      <div class="icon bg-red"><i class="icon-bill"></i></div>
                      <div class="title"><span>Avg<br>Salary</span>
                      </div>
                      <div class="number"><strong><c:out value="${avgSalary}" /></strong></div>
                    </div>
                  </div>
                  <!-- Item -->
                  <div class="col-xl-4 col-sm-6">
                    <div class="item d-flex align-items-center">
                      <div class="icon bg-green"><i class="fa fa-align-left"></i></div>
                      <div class="title"><span>Avg Emps by<br>Departments</span>

                      </div>
                      <div class="number"><strong><c:out value="${avgEmpsByDept}" /></strong></div>
                    </div>
                  </div>

                </div>
              </div>
            </section>

            <section>
              <div class="container-fluid">
                <div class="col-lg-12">
                  <div class="bar-chart-example card">
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">Salary By Job Title</h3>
                    </div>
                    <div class="card-body">
                      <canvas id="barChartExample"></canvas>
                    </div>
                  </div>
                </div>
              </div>
            </section>
            <section>   
              <div class="container-fluid">
                <div class="col-lg-12">
                  <div class="line-chart-example card no-margin-bottom">

                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">No. of Hires by Year</h3>
                    </div>
                    <div class="card-body">
                      <canvas id="lineChartExample1"></canvas>
                    </div>
                  </div>
                </div>
              </div>
            </section>
            <!-- Employees --> 
          </c:if>
          <c:if test = "${displayPage == 2}"> 
            <section class="tables">   
              <div class="container-fluid">
                <div class="row">


                  <div class="col-lg-12">
                    <div class="card">
                      <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Employees</h3>
                      </div>
                      <div class="card-body">
                        <div class="table-responsive">                       
                          <table class="table table-striped table-hover" id="userTable">
                            <thead>
                              <tr>
                                <th>First Name</th>
                                <th>Last name</th>
                                <th>Job title</th>
                                <th>Department name</th>
                                <th>Manager name</th>
                                <th>Salary</th>
                                <th>Commission</th>
                                <th>Hire date</th>
                                <th>Email</th>
                                <th>Phone number</th>
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${employees}" var="employee">

                                <tr>
                                  <td class="admin"><c:out value="${employee.firstName}" /></td>
                                  <td class="admin"><c:out value="${employee.lastName}" /></td>
                                  <td class="admin"><c:out value="${employee.jobId.jobTitle}" /></td>
                                  <td class="admin"><c:out value="${employee.departmentId.departmentName}" /></td>
                                  <td class="admin"><c:out value="${employee.managerId.firstName}" /> <c:out value="${employee.managerId.lastName}" /></td>
                                  <td class="admin"><c:out value="${employee.salary}" /></td>
                                  <td class="admin"><c:out value="${employee.commissionPct}" /></td>
                                  <td class="admin"><c:out value="${employee.hireDateFormatted}" /></td>
                                  <td class="admin"><c:out value="${employee.email}" /></td>
                                  <td class="admin"><c:out value="${employee.phoneNumber}" /></td>
                                </tr>
                              </c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>

                </div>
              </div>
            </section>

          </c:if>
          <c:if test = "${displayPage == 3}"> 
            <section class="projects no-padding-top">
              <c:import url="changesalary.jsp"></c:import>
              </section>
          </c:if>
          <c:if test = "${displayPage == 4}"> 
            <section class="projects no-padding-top">
              <c:import url="addnewemployee.jsp"></c:import>
              </section>
          </c:if> 

          <div class="modal fade" tabindex="-1" role="dialog" id="salarySuccess">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Salary change</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <p>Salary successfully modified!</p>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Ok</button>
                </div>
              </div>
            </div>
          </div>

          <!-- Updates Section                                                -->
          <section class="updates no-padding-top">
            <div class="container-fluid"> </div>
          </section>
          <!-- Page Footer-->
          <footer class="main-footer">
            <div class="container-fluid">
              <div class="row">
                <div class="col-sm-6">
                  <p>Garbage Collector &copy; 2017-2018</p>
                </div>
                <div class="col-sm-6 text-right">
                  <p>Design by <a href="https://bootstrapious.com/admin-templates" class="external">Bootstrapious</a></p>
                  <!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)-->
                </div>
              </div>
            </div>
          </footer>
        </div>
      </div>
    </div>

    <!-- JavaScript files-->
    <script src="resources/vendor/jquery/jquery.min.js"></script>
    <script src="resources/vendor/popper.js/umd/popper.min.js"></script>
    <script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="resources/vendor/jquery.cookie/jquery.cookie.js"></script>
    <script src="resources/vendor/chart.js/Chart.min.js"></script>
    <script src="resources/vendor/jquery-validation/jquery.validate.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
    <!-- Main File
    <script src="resources/js/charts-custom.js"></script>-->
    <script src="resources/js/front.js"></script>

    <script>
      $(document).ready(function () {
        if (${salaryChange} === 1) {
          $('#salarySuccess').modal('show');
        }
        var LINECHART1 = $('#lineChartExample1');
        var myLineChart = new Chart(LINECHART1, {
          type: 'line',
          options: {
            scales: {
              xAxes: [{
                  display: true,
                  gridLines: {
                    display: true
                  }
                }],
              yAxes: [{
                  ticks: {
                    max: 30,
                    min: 0,
                    stepSize: 10
                  },
                  display: true,
                  gridLines: {
                    display: true
                  }
                }]
            },
            legend: {
              display: false
            }
          },
          data: {
            labels: ${hireYears},
            datasets: [
              {
                label: "New employees",
                fill: true,
                lineTension: 0,
                backgroundColor: "transparent",
                borderColor: '#6ccef0',
                pointBorderColor: '#59c2e6',
                pointHoverBackgroundColor: '#59c2e6',
                borderCapStyle: 'butt',
                borderDash: [],
                borderDashOffset: 0.0,
                borderJoinStyle: 'miter',
                borderWidth: 3,
                pointBackgroundColor: "#59c2e6",
                pointBorderWidth: 0,
                pointHoverRadius: 4,
                pointHoverBorderColor: "#fff",
                pointHoverBorderWidth: 0,
                pointRadius: 4,
                pointHitRadius: 0,
                data: ${hireCounts},
                spanGaps: false
              }
            ]
          }
        });
      });
    </script>

    <script>


      $(document).ready(function () {

        var ctx1 = $("canvas").get(0).getContext("2d");
        var gradient1 = ctx1.createLinearGradient(150, 0, 150, 300);
        gradient1.addColorStop(0, 'rgba(133, 180, 242, 0.91)');
        gradient1.addColorStop(1, 'rgba(255, 119, 119, 0.94)');

        var gradient2 = ctx1.createLinearGradient(146.000, 0.000, 154.000, 300.000);
        gradient2.addColorStop(0, 'rgba(104, 179, 112, 0.85)');
        gradient2.addColorStop(1, 'rgba(76, 162, 205, 0.85)');
        var BARCHARTEXMPLE = $('#barChartExample');
        var barChartExample = new Chart(BARCHARTEXMPLE, {
          type: 'bar',
          options: {
            scales: {
              xAxes: [{
                  display: true,
                  gridLines: {
                    color: '#eee'
                  }
                }],
              yAxes: [{
                  display: true,
                  gridLines: {
                    color: '#eee'
                  }
                }]
            },
          },
          data: {
            labels: ${chartLabels},
            datasets: [
              {
                label: "Min Salary",
                backgroundColor: [
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9'
                ],
                borderColor: [
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9',
                  '#2b90d9'
                ],
                borderWidth: 1,
                data: ${chartMin},
              },
              {
                label: "Max Salary",
                backgroundColor: [
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676'
                ],

                borderColor: [
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676',
                  '#ff7676'
                ],
                borderWidth: 1,
                data: ${chartMax},
              }
            ]
          }
        });

      });
    </script>
    <script>
      $(document).ready(function () {

        $('#userTable').DataTable();

      });
    </script>
  </body>
</html>