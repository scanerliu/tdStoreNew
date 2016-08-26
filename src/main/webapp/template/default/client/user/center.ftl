<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>个人中心首页 - ${system.webkeywords!''}</title>
    <!-- 网站图标 -->
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">
	<#include "/common/common.ftl" />
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/user/user.js"></script>
    <!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
</head>
<body>
<h1 style="display:none;"></h1>
<!-- Header -->
<#include "../common/centerheader.ftl">
<!-- Header -->
<div class="clear"></div>

<!-- Center Start -->
<div class="index-main">
    <div class="personal-center">
        <!-- 左侧导航 -->
        <#include "../common/centerleftmenu.ftl">
        
        <!-- 右侧内容 -->
        <div class="right-content">
            <!-- 个人信息 -->
            <div class="personal-info">
                <div class="head-img">
                	<#if currentUser.uavatar??>
	                	<#if currentUser.uavatar?index_of("http")==0>
	                	<img src="${currentUser.uavatar!''}" alt="暂无头像">
	                	<#else>
				        <img src="${app.basePath}${currentUser.uavatar!''}" alt="暂无头像">
				        </#if>
			        <#else>
			        <img src="${app.basePath}/static/default/mobile/images/defaultavtar.png" alt="暂无头像">
			        </#if>
                </div>
                <div class="user-name">
                    <div class="div1">
                        <label>Sweet</label>
                        <label class="grade"><em><#if membership??>${membership.name!''}</#if></em>会员</label>
                    </div>
                    <div class="bank-card">
                        <a href="javascript:void(0);" class="clickcard" title="" onclick="tipshide($(this));">打卡签到</a>

                    </div>

                    <script>
                        $(function(){

                        });
                    </script>
                    <div class="div3">
                        <div>账户安全</div>
                        <div class="div2"><p></p></div>
                        <div>较低</div>
                    </div>
                </div>
                <div class="myinfo">
                    <div class="ininfo">
                        <strong class="p1">钱包余额</strong>
                        <span class="p2"><strong class="orange">${currentUser.userAccount.amount!'0'}</strong>元</span>
                    </div>
                    <div class="ininfo">
                        <strong class="p1">我的积分</strong>
                        <span class="p3"><strong id="uintegral">${integral.integral!'0'}</strong>分</span>
                    </div>

                    <div class="t-nav">
                        <a href="#" title="">
                            <img src="${app.basePath}/static/default/client/images/icon_pc_nav_1.png" alt=""/>

                            <p>待付款<span>0</span></p>
                        </a>
                        <a href="#" title="">
                            <img src="${app.basePath}/static/default/client/images/icon_pc_nav_2.png" alt=""/>

                            <p>待收货<span>0</span></p>
                        </a>
                        <a href="#" title="">
                            <img src="${app.basePath}/static/default/client/images/icon_pc_nav_3.png" alt=""/>
                            <p>待评价<span>0</span></p>
                        </a>
                    </div>
                </div>


            </div>
            <!-- 我的订单 -->
            <div class="my-orders">
                <div class="top-line">
                    <p class="p1">我的订单</p>

                    <p>待付款<span>5</span></p>

                    <p>待发货<span>3</span></p>

                    <p>待评价<span>0</span></p>
                    <a href="#">查看全部订单</a>
                </div>
                <ul class="ul1">
                    <li class="li1">
                        <a class="img" href="#">
                            <img src="images/2.jpg" alt=""/>
                        </a>
                        <a class="img" href="#">
                            <img src="images/3.jpg" alt=""/>
                        </a>
                    </li>
                    <li class="li2">2015-12-12 已发货</li>
                    <li class="li3">
                        <span class="span1">查看物流 v</span>

                        <div class="wu-l">
                            <img class="uarrow" src="images/icon_pc_uarrow_1.png" alt=""/>

                            <div class="lists">
                                <div class="p1">中国邮政：9640024911621</div>
                                <div class="p2 active">
                                    <sup>·</sup>

                                    <p>
                                        <span>离开【长沙】，下一站【成都中心】</span>
                                        <span>2016-02-02 05:43:57</span>
                                    </p>
                                </div>
                                <div class="p2">
                                    <sup>·</sup>

                                    <p>
                                        <span>到达【长沙邮区中心局邮件处理中心】</span>
                                        <span>2016-02-02 01:14:00</span>
                                    </p>
                                </div>
                                <div class="p2 p3">
                                    <sup>·</sup>

                                    <p>以上为最新跟踪信息，<a href="#">查看全部</a></p>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="li4">
                        <a href="#">确认收货</a>
                    </li>
                </ul>
                <ul>
                    <li class="li1">
                        <a class="img" href="#">
                            <img src="images/3.jpg" alt=""/>
                        </a>
                        <a class="img" href="#">
                            <img src="images/4.jpg" alt=""/>
                        </a>
                    </li>
                    <li class="li2">2015-12-12 已签收</li>
                    <li class="li3">
                        <span class="span1">查看物流 v</span>

                        <div class="wu-l">
                            <img class="uarrow" src="images/icon_pc_uarrow_1.png" alt=""/>

                            <div class="lists">
                                <div class="p1">中国邮政：9640024911621</div>
                                <div class="p2 active">
                                    <sup>·</sup>

                                    <p>
                                        <span>离开【长沙】，下一站【成都中心】</span>
                                        <span>2016-02-02 05:43:57</span>
                                    </p>
                                </div>
                                <div class="p2">
                                    <sup>·</sup>

                                    <p>
                                        <span>到达【长沙邮区中心局邮件处理中心】</span>
                                        <span>2016-02-02 01:14:00</span>
                                    </p>
                                </div>
                                <div class="p2 p3">
                                    <sup>·</sup>

                                    <p>以上为最新跟踪信息，<a href="#">查看全部</a></p>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="li4">
                        <a href="#">去评价</a>
                    </li>
                </ul>
                <div class="noorder">
                    <i></i><label class="lbltips">这里都是空空的，快去挑选合适的商品吧！</label>
                </div>
            </div>

            <!--猜您喜欢-->
            <form id="enjoyForm">
			<input type="hidden" name="pageNo" id="enjoysc_pageNo" value="1">
			<input type="hidden" name="pageSize" value="8"/>
			</form>
            <div class="guess-like">
                <div class="title">
                    <strong>猜你喜欢</strong>
                </div>
                <div class="pro-list">
                    <ul id="enjoyList">
                    </ul>
                </div>

            </div>

        </div>
        <!-- 右侧内容 END -->

    </div>
    <div class="clear"></div>
</div>
<!-- Center End -->
<!-- Footer Start -->
<#include "../common/commonfooter.ftl">
<!-- Footer End -->
<script>
	$(function(){
		getenjoyproducts();
	});
</script>
</body>
</html>
