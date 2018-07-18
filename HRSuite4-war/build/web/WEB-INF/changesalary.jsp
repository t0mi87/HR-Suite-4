<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="employeesJson" value="${employeesJson}"/>
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
            <h3 class="h4">Change salary</h3>
          </div>
          <div class="card-body">
            <form class="form-horizontal needs-validation" action="home" method="post" name="home" novalidate>

              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Department</label>
                <div class="col-sm-9 select">
                  <select name="department" id="department" class="form-control">
                     <option value="" disabled selected>Select your option</option>
                    <c:forEach items="${departments}" var="department">

                      <option><c:out value="${department.departmentName}" /></option>

                    </c:forEach>

                  </select>
                </div>
              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Employee</label>
                <div class="col-sm-9 select">
                  <select name="employee" id="employee" class="form-control" required="" placeholder="Please select">
                  </select>
                  <small class="help-block-none">Select a Department first</small>
                </div>
              </div>
              <div class="line"></div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Set New Salary</label>
                <div class="col-sm-9">

                  <input type="number" placeholder="Enter new salary" class="form-control" name="salary" required="true">
                  
                </div>
                <div class="line"></div>


                <div class="card-body text-center">
  
                 <input type="submit" class="btn btn-primary" id="home" value="Confirm salary modification">
                  <!-- Modal-->
                  <!--<div id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" class="modal fade text-left">
                    <div role="document" class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h4 id="exampleModalLabel" class="modal-title">Confirm</h4>
                          <button type="button" data-dismiss="modal" aria-label="Close" class="close"><span aria-hidden="true">x</span></button>
                        </div>
                        <div class="modal-body">
                          <p>Please review the changes you are about to commit</p>
      
                            <div class="form-group">
                              <label>Current Salary</label>

                            </div>
                            <div class="form-group">       
                              <label>New Salary</label>
                            </div>
  
                        </div>
                        <div class="modal-footer">
                          <button type="button" data-dismiss="modal" class="btn btn-secondary">Cancel</button>
                          <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                      </div>
                    </div>
                  </div>-->
                </div>
            </form>

          </div>
        </div>

      </div>

    </div>
    <div class="col-lg-2"> </div>
  </div>
</div>

</section>

<script>
  $(document).ready(function () {

    $('#department').change(function () {
      var selectedDept = $("#department option:selected").text();

      $('#employee').empty();
      $.each(${employeesJson}, function (i, item) {
        var empId = item.split(";")[1];
        var empName = item.split(";")[0];
        var deptName = item.split(";")[2];
        
        if (selectedDept === deptName) {
          
          $('#employee').append($('<option>', {
            value: empId,
            text: empName
          }));
        }
      });
    });

  });
</script>
<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
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