<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Breadcrumb-->
<div class="breadcrumb-holder container-fluid"> </div>
<!-- Forms Section-->
<section class="forms"> 
  <div class="container-fluid">
    <div class="row">
      <!-- Basic Form-->
      <!-- Horizontal Form-->
      <!-- Inline Form-->
      <!-- Modal Form-->
      <div class="col-lg-2"> </div>
      <!-- Form Elements -->
      <div class="col-lg-8">
        <div class="card">
          <div class="card-header d-flex align-items-center">
            <h3 class="h4">New Employee</h3>
          </div>
          <div class="card-body">
            <form class="form-horizontal needs-validation" id="addEmployeeForm" novalidate>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">First Name</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" name="firstName" required="">
                  <div class="invalid-feedback">
                    Please enter a first name.
                  </div>
                </div>
              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Last Name</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" name="lastName" required="">
                  <div class="invalid-feedback">
                    Please enter a last name.
                  </div>
                </div>
              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Department</label>
                <div class="col-sm-9 select">
                  <select name="department" class="form-control">
                    <option>Dinamikusan töltődjön</option>
                    <option>Dept1</option>
                    <option>Dept2</option>
                    <option>Satöbbi</option>
                  </select>
                </div>
              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Job Title</label>
                <div class="col-sm-9 select">
                  <select name="jobtitle" class="form-control">
                    <option>Dinamikusan tÃ¶ltÅdjÃ¶n</option>
                    <option>Job Title1</option>
                    <option>Job Title2</option>
                    <option>SatÃ¶bbi</option>
                  </select>
                </div>
              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Manager</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" name="manager">
                </div>

              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Hire Date</label>
                <div class="col-sm-9">
                  <input type="text" id="hiredate" class="form-control" name="hiredate">
                </div>
              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Salary</label>
                <div class="col-sm-9">
                  <input type="number" class="form-control" name="salary" required="">
                </div>
              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Commission</label>
                <div class="col-sm-9">
                  <input type="number"  class="form-control" name="commission" value="0">
                </div>
              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Email</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" name="email">
                </div>
              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Phone Number</label>
                <div class="col-sm-9">
                  <input type="text" required="" class="form-control" name="phonenumber">
                </div>
              </div>
              <div class="line"></div>
              <div class="col-sm-8">
                <button type="submit" class="btn btn-primary" value="addemployee">Add Employee</button>
                <button class="btn btn-secondary" id="resetBtn">Reset</button>
                <button class="btn btn-secondary" action="home?displayPage=1">Cancel</button>
              </div>

          </div>
        </div>
        </form>
      </div>
      <div class="col-lg-2"> </div>
    </div>
  </div>
  <script>
  // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
      'use strict';
      window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function (form) {
          form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
              event.preventDefault();
              event.stopPropagation();
            }
            form.classList.add('was-validated');
          }, false);
        });
      }, false);
    })();
  </script>
  <script>
    $(document).ready(function () {
      Date.prototype.toDateInputValue = (function () {
        var local = new Date(this);
        local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
        return local.toJSON().slice(0, 10);
      });
      //$('#hiredate').val(new Date().toDateInputValue());
      document.getElementById('hiredate').value = new Date().toDateInputValue();
    })
  </script>
