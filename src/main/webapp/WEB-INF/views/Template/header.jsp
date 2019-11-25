
<%
//    Accounts account = new Accounts();
//    if (new SessionUtils().getSessionValue(request, Constant.ADMIN) != null) {
//        account = (Accounts) new SessionUtils().getSessionValue(request, Constant.ADMIN);
//    }   

%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta name="description" content="Vuesax admin is super flexible, powerful, clean &amp; modern responsive bootstrap 4 admin template with unlimited possibilities.">
        <meta name="keywords" content="admin template, Vuesax admin template, dashboard template, flat admin template, responsive admin template, web app">
        <meta name="author" content="PIXINVENT">
        <title>Analytics Cards - Vuesax - Bootstrap HTML admin template</title>
        <link rel="apple-touch-icon" href="<%=request.getContextPath()%>/assets-new/app-assets/images/ico/apple-icon-120.png">
        <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/assets-new/app-assets/images/ico/favicon.ico">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">

        <!-- BEGIN: Vendor CSS-->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/vendors.min.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/charts/apexcharts.css">
        <!-- END: Vendor CSS-->

        <!-- BEGIN: Theme CSS-->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/css/bootstrap-extended.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/css/colors.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/css/components.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/css/themes/dark-layout.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/css/themes/semi-dark-layout.css">

        <!-- BEGIN: Page CSS-->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/css/core/menu/menu-types/vertical-menu.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/css/core/colors/palette-gradient.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/css/pages/card-analytics.css">
        <!-- END: Page CSS-->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/assets/css/style.css">

        <!-- BEGIN: Custom CSS-->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/custom.css">
        <!-- END: Custom CSS-->

    </head>

    <body class="vertical-layout vertical-menu-modern 2-columns  navbar-floating footer-static   menu-collapsed" data-open="click" data-menu="vertical-menu-modern" data-col="2-columns">
        <jsp:include page="../Template/menu.jsp"></jsp:include>
        <div class="app-content content">

            <!-- BEGIN: Header-->
            <div class="content-overlay"></div>
            <div class="header-navbar-shadow"></div>
            <nav class="header-navbar navbar-expand-lg navbar navbar-with-menu floating-nav navbar-light navbar-shadow">
                <div class="navbar-wrapper">
                    <div class="navbar-container content">
                        <div class="navbar-collapse" id="navbar-mobile">
                            <div class="mr-auto float-left bookmark-wrapper d-flex align-items-center">
                                <ul class="nav navbar-nav">
                                    <li class="nav-item mobile-menu d-xl-none mr-auto"><a class="nav-link nav-menu-main menu-toggle hidden-xs" href="#"><i class="ficon feather icon-menu"></i></a></li>
                                </ul>
                            </div>
                            <ul class="nav navbar-nav float-right">
                                <li class="nav-item d-none d-lg-block"><a class="nav-link nav-link-expand"><i class="ficon feather icon-maximize"></i></a></li>

                                <li class="dropdown dropdown-user nav-item"><a class="dropdown-toggle nav-link dropdown-user-link" href="#" data-toggle="dropdown">
                                        <div class="user-nav d-sm-flex d-none"><span class="user-name text-bold-600">
                                                <%--= account.getFirstname()%> <%= account.getLastname()!=null ? account.getLastname() :"" --%>hetl
                                            </span></div><span><img class="round" src="<%=request.getContextPath()%>/assets-new/app-assets/images/common.png" alt="avatar" height="40" width="40" /></span>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a class="dropdown-item" href="<%= request.getContextPath()%>/AdminProfile/"><i class="feather icon-user"></i> My Profile</a>                                        
                                        <a class="dropdown-item" href="<%= request.getContextPath()%>/Admin/logout"><i class="feather icon-power"></i> Logout</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </nav>

