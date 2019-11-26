

<!-- BEGIN: Main Menu-->
<div class="main-menu menu-fixed menu-light menu-accordion menu-shadow" data-scroll-to-active="true">
    <div class="navbar-header">
        <ul class="nav navbar-nav flex-row">
            <li class="nav-item mr-auto"><a class="navbar-brand">
                                        <div class="brand-logo"></div>
<!--                    <img src="<%=request.getContextPath()%>/assets-new/app-assets/images/logo-admin.png" alt="" width="120" style="height:32px;">-->
                    <h2 class="brand-text mb-0"></h2>
                </a></li>
            <li class="nav-item nav-toggle"><a class="nav-link modern-nav-toggle pr-0" data-toggle="collapse"><i class="feather icon-x d-block d-xl-none font-medium-4 primary toggle-icon"></i><i class="toggle-icon feather icon-disc font-medium-4 d-none d-xl-block collapse-toggle-icon primary" data-ticon="icon-disc"></i></a></li>
        </ul>
    </div>
    <div class="shadow-bottom"></div>
    <div class="main-menu-content">
        <ul class="navigation navigation-main" id="main-menu-navigation" data-menu="menu-navigation">
             <li  class=" <%--=uu.containsKey("Dashboard") ? "active" : ""--%> nav-item">
                <a href="<%=request.getContextPath()%>/Dashboard/Dashboard"><i class="feather icon-home"></i><span class="menu-title" data-i18n="Dashboard">Dashboard</span></a>
            </li>
            <li  class=" <%--=uu.containsKey("Dashboard") ? "active" : ""--%> nav-item">
                <a href="<%=request.getContextPath()%>/customer/List"><i class="feather icon-home"></i><span class="menu-title" data-i18n="Customer">Customer</span></a>
            </li>

            <li class=" nav-item"><a href="<%=request.getContextPath()%>/driver/List" title="Driver"><i class="feather icon-user"></i><span class="menu-title" data-i18n="Email">Driver</span></a>
            </li>
             <li class=" nav-item"><a href="<%=request.getContextPath()%>/job/List" title="Job"><i class="feather icon-user"></i><span class="menu-title" data-i18n="Email">Job</span></a>
            </li>
        </ul>
    </div>
</div>
<!-- END: Main Menu-->
