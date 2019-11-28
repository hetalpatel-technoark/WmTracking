<%@page import="java.util.Iterator"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="com.wmtrucking.entities.MaDriver"%>
<%@page import="java.util.List"%>
<jsp:include page="../Template/header.jsp"></jsp:include>

    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Customers</h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/customer/List">Customers</a>
                            </li>
                            <li class="breadcrumb-item"><a href="#">Edit</a>
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
                        if (request.getAttribute("error") != null) {
                            List<String> errors = (List<String>) request.getAttribute("error");
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
        MaDriver madriver = null;
                        if (request.getAttribute("maDriver") != null) {
                            madriver = (MaDriver) request.getAttribute("maDriver");
                        }

                        CheckInput checkInput = new CheckInput();
                    %>

                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <form method="post" id="adform" class="form-horizontal bordered-row" id="user-form" action="<%=request.getContextPath()%>/driver/postEdit">
                                    <input type="hidden" name="id" value="<%=madriver.getId() %>" >

                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>First Name*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" required id="first-name" class="form-control" name="fname" value="<%=checkInput.checkValueEdit(madriver.getFirstname(),request.getParameter("fname"))%>" placeholder="First Name">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Middle Name</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="mname" value="<%=checkInput.checkValueEdit (madriver.getMiddlename(),request.getParameter("mname"))%>" placeholder="Middle Name">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Last Name</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="lname" value="<%=checkInput.checkValueEdit (madriver.getLastname(),request.getParameter("lname"))%>" placeholder="Last Name">
                                                    </div>
                                                </div>


                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>License number</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="lno" value="<%=checkInput.checkValueEdit (madriver.getLicensenumber(),request.getParameter("lno"))%>" placeholder="License number">
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Mobile Number*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" required="" id="first-name" class="form-control" name="mob" value="<%=checkInput.checkValueEdit (madriver.getMobile(),request.getParameter("mob"))%>" placeholder="Mobile Number">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>email*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="email" required="" id="first-name" class="form-control" name="email" value="<%=checkInput.checkValueEdit (madriver.getEmail(),request.getParameter("email"))%>" placeholder="Email">
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>address1</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="add1" value="<%=checkInput.checkValueEdit (madriver.getAddress1(),request.getParameter("add1"))%>" placeholder="Address 1">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>address2</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="add2" value="<%=checkInput.checkValueEdit (madriver.getAddress2(),request.getParameter("add2"))%>" placeholder="Address 2">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>address3</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="add3" value="<%=checkInput.checkValueEdit (madriver.getAddress3(),request.getParameter("add3"))%>" placeholder="Address 3">
                                                    </div>
                                                </div>


                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>city</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text"  id="first-name" class="form-control" name="city" value="<%=checkInput.checkValueEdit (madriver.getCity(),request.getParameter("city"))%>" placeholder="City">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>state</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="state" class="form-control" name="state" value="<%=checkInput.checkValueEdit (madriver.getState(),request.getParameter("state"))%>" placeholder="State">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Country</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="country" class="form-control" name="country" value="<%=checkInput.checkValueEdit (madriver.getCountry(),request.getParameter("country"))%>" placeholder="Country">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Pincode</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text"  id="pincode" class="form-control" name="pin" value="<%=checkInput.checkValueEdit (madriver.getPincode(),request.getParameter("pin"))%>" placeholder="Pincode">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-8 offset-md-4">
                                                <button type="submit" class="btn btn-primary mr-1 mb-1">Update Information</button>
                                                <a href="<%= request.getContextPath()%>/driver/drivelist" class="btn btn-danger mr-1 mb-1 waves-effect waves-light">Cancel</a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
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