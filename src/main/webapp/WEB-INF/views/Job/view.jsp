<%@page import="java.util.Iterator"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="com.wmtrucking.entities.MaCustomer"%>
<%@page import="com.wmtrucking.entities.MaJobs"%>
<%@page import="com.wmtrucking.entities.MaDriver"%>
<%@page import="com.wmtrucking.utils.DateUtils"%>

<%@page import="java.util.List"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/pickers/pickadate/pickadate.css">  

<jsp:include page="../Template/header.jsp"></jsp:include>

    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Driver</h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/job/List">Job</a>
                            </li>
                            <li class="breadcrumb-item"><a href="#">View</a>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>        
    </div>
    <div class="content-body">
        <!-- Basic Horizontal form layout section start -->
        <section id="basic-horizontal-layouts">
            <div class="row match-height">
                <div class="col-md-12 col-12">
                    <%
                        if (request.getAttribute("errors") != null) {
                            List<String> errors = (List<String>) request.getAttribute("errors");
                            if (!errors.isEmpty()) {
                    %>
                    <div class="alert alert-danger">
                        <button class="close" data-dismiss="alert"><span>x</span></button>
                        <%
                                Iterator<String> iterator = errors.iterator();
                                while (iterator.hasNext()) {
                                    out.println("<i class='fa fa-arrow-circle-right'></i> " + iterator.next() + "<br/>");
                                }
                            }
                        %>
                    </div>
                    <%                        }
                        CheckInput checkInput = new CheckInput();
  DateUtils dateUtils = new DateUtils();
        MaJobs majob = null;
                        if (request.getAttribute("maJobs") != null) {
                            majob = (MaJobs) request.getAttribute("maJobs");
                        }

                    %>

                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                    <input readonly type="hidden" name="id" value="<%=checkInput.checkValue(majob.getId())%>">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Job number*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input readonly  type="text" id="first-name" class="form-control" name="jno" value="<%=checkInput.checkValueEdit(majob.getJobnumber() ,request.getParameter("jno"))%>" placeholder="Job number">
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Customer *</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <select readonly class="form-control select-class" name="customer" id="advertisementType" required>
                                                            <%
                                                            List<MaCustomer> maCustomers = (List<MaCustomer>) request.getAttribute("maCustomer");
 for (MaCustomer maCustomer : maCustomers) {
                                                            %>
                                                            <option <%= maCustomer.getId().equals(checkInput.checkValueEdit(majob.getCustId().getId(),request.getParameter("customer")))?"selected":"" %>  value="<%=maCustomer.getId() %>"><%= maCustomer.getFirstname()%></option>
                                                            <%}%>
                                                        </select>
                                                    </div>
                                                </div> 
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Driver *</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <select readonly class="form-control select-class" name="driver" id="advertisementType" required>
                                                            <%
 List<MaDriver> maDrivers = (List<MaDriver>) request.getAttribute("maDriver");
 for (MaDriver maDriver : maDrivers) {
                                                            %>
                                                            <option <%= maDriver.getId().equals(checkInput.checkValueEdit(majob.getDriverId().getId(),request.getParameter("driver")))?"selected":"" %>  value="<%=maDriver.getId() %>"><%= maDriver.getFirstname()%></option>
                                                            <%}%>
                                                        </select>
                                                    </div>
                                                </div> 



                                            </div>
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">                                                    
                                                        <span>Job date</span></div>
                                                    <div class="col-md-8">
                                                        <input readonly type="text" readonly id="expirydate" name="jobdate" value="<%=checkInput.checkValueEdit(dateUtils.dateWithFormat(majob.getJobdate(), "dd-MM-yyyy"),request.getParameter("jobdate"))%>" class="form-control pickadate1" placeholder="Job Date">
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Select fill</span></div>
                                                    <div class="col-sm-8">
                                                        <div class="col-sm-12">
                                                            <ul class="list-unstyled mb-0">
                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input readonly type="checkbox" class="custom-control-input" <%= (majob.getHauloff()!=null&&majob.getHauloff().equals(checkInput.checkValueEdit(majob.getHauloff(),request.getParameter("haulOff"))))?"checked":"" %>  value="haulOff" name="haulOff" id="customCheck1">
                                                                            <label class="custom-control-label" for="customCheck1">Haul Off </label>
                                                                        </div>
                                                                    </fieldset></li>
                                                                <br>
                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input readonly type="checkbox" class="custom-control-input" <%= majob.getHaulback()!=null&&majob.getHaulback().equals(checkInput.checkValueEdit(majob.getHaulback(),request.getParameter("haulBack")))?"checked":"" %> value="haulBack"  name="haulBack" id="customCheck2">
                                                                            <label class="custom-control-label" for="customCheck2">Haul Back</label>
                                                                        </div>
                                                                    </fieldset></li>
                                                            </ul>
                                                        </div> 
                                                    </div>
                                                </div>



                                            </div>
                                            <div class="col-md-8 offset-md-4">
                                                <a href="<%= request.getContextPath()%>/job/List" class="btn btn-danger mr-1 mb-1 waves-effect waves-light">Cancel</a>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- // Basic Horizontal form layout section end -->



    </div>
</div>

<!-- END: Content-->
<jsp:include page="../Template/pageEnd.jsp"></jsp:include>    

<jsp:include page="../Template/footer.jsp"></jsp:include>
    <script>
        $(document).ready(function () {
            $(".pickadate1").pickadate({
                format: "mm-dd-yyyy"
            });


        });

    </script>

    <script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.date.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.time.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/legacy.js"></script>

<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/pickers/dateTime/pick-a-datetime.js"></script>