$(document).ready(function() {
    if ($.cookie("saveUser") == "true") {
        $("#saveUser").attr("checked", true);
        $("#username").val($.cookie("username"));
        $("#password").val($.cookie("password"));
    }
});

function saveUserInfo() {
    if ($("#saveUser").attr("checked")) {
        var username = $("#username").val();
        var password = $("#password").val();
        $.cookie("saveUser", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
        $.cookie("username", username, { expires: 7 }); // 存储一个带7天期限的 cookie
        $.cookie("password", password, { expires: 7 }); // 存储一个带7天期限的 cookie
    }
    else {
        $.cookie("saveUser", "false", { expires: -1 }); // 删除 cookie
        $.cookie("username", "", { expires: -1 });
        $.cookie("password", "", { expires: -1 });
    }
}