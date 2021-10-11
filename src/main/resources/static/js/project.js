let global_hs_name;
let global_hs_url;
const selectedIcon = function (doc) {
    global_hs_url = $(doc).children("img").attr("src");
    if ($(doc).next().css("display") == "none") {
        $(doc).siblings().show();
    } else {
        $(doc).siblings().hide();
    }
}

const draw_hotspot = function (url, hs_name, ath, atv, distorted, removable) {
    krpano.call("addhotspot(" + hs_name + ")");
    krpano.set("hotspot[" + hs_name + "].url", url);
    krpano.set("hotspot[" + hs_name + "].ath", ath);
    krpano.set("hotspot[" + hs_name + "].atv", atv);
    krpano.set("hotspot[" + hs_name + "].distorted", distorted);
    if (removable) {
        krpano.set("hotspot[" + hs_name + "].ondown", "draghotspot()");
    }

    if (krpano.get("device.html5")) {
        // for HTML5 it's possible to assign JS functions directly to krpano events
        krpano.set("hotspot[" + hs_name + "].onclick", function (hs) {
            layer.msg(hs_name);
        }.bind(null, hs_name));
    } else {
        // for Flash the js() action need to be used to call from Flash to JS (this code would work for Flash and HTML5)
        krpano.set("hotspot[" + hs_name + "].onclick", "js( layer.msg(calc('\"' + name + '\"')) );");
    }
}

const getHotspot = function (sceneId) {
    $.get({
        url: "/monitor/hotspot/getHotspotBySceneId",
        data:{"sceneId": sceneId},
        success: function (result) {
            result.data.forEach(hotspot=>{
                draw_hotspot(hotspot.hotspotUrl, hotspot.hotspotName, hotspot.hotspotAth, hotspot.hotspotAtv, hotspot.hotspotDistorted);
            })
        }
    });
};

function add_hotspot() {
    let chooseIcon = data => {
        let iconHtml = "";
        data.data.forEach(iconObj => {
            iconHtml = iconHtml + "<div onclick='selectedIcon(this)' class='icon' style='float: left; border: 3px;'>" +
                "<img style='width: 30px;height: 30px' src=\"" + iconObj.iconImgUrl + "\">" +
                "</div>";
        });
        layer.open({
            title: '添加物证',
            closeBtn: 0, //不显示关闭按钮
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['320px', '350px'], //宽高
            content: '<div class="add_hotspot_layer">' +
                '<table cellpadding="0" cellspacing="0" width="100%">' +
                '<td width="100" align="right" valign="top">选择图标：</td>' +
                '<td>' + iconHtml +
                '</td>' +
                '</tr>' +
                '</div>',
            btn: ['确定', '关闭'],
            yes: function () {
                global_hs_name = "hs" + ((Date.now() + Math.random()) | 0);	// create unique/randome name
                const ath = krpano.get("view.hlookat");
                const atv = krpano.get("view.vlookat");
                draw_hotspot(global_hs_url, global_hs_name, ath, atv, true, true);
                $("#add_hotspot").hide()
                $("#confirm_hotspot").show();
                layer.closeAll();
            }
        });
    }
    $.get({url: "/monitor/panoIcon/getAllPanoIcon", success: chooseIcon});
}


function confirm_hotspot() {
    layer.open({
        title: '添加物证',
        closeBtn: 0, //不显示关闭按钮
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['520px', '400px'], //宽高
        content: '<div class="add_hotspot_layer">' +
            '<table cellpadding="0" cellspacing="0" width="100%">' +
            '<tr>' +
            '<td width="100" align="right" valign="top">物证名称：</td>' +
            '<td>' +
            '<div class="input1"><input id="hotspot_name" type="text"></div>' +
            '<div class="td1">*物证名称只能包含中文、英文、下划线、数字</div>' +
            '</td>' +
            '</tr>' +
            '</div>',
        btn: ['确定', '关闭'],
        yes: function (index, layero) {
            let hostName = $("#hotspot_name").val();
            let sceneId = krpano.get("xml.scene").replace("krpano", "");
            let url = krpano.get("hotspot[" + global_hs_name + "].url");
            let ath = krpano.get("hotspot[" + global_hs_name + "].ath");
            let atv = krpano.get("hotspot[" + global_hs_name + "].atv");
            let distorted = krpano.get("hotspot[" + global_hs_name + "].distorted");
            $("#add_hotspot").show()
            $("#confirm_hotspot").hide();

            if (new RegExp('^\d').test(hostName)) {
                layer.msg("名称不能以数字开头");
                return;
                return;
            }

            $.ajax({
                url: "/monitor/hotspot/addHotspot",
                type: "post",
                dataType: "json",
                data: JSON.stringify({
                    "sceneId": sceneId,
                    "hotspotName": hostName,
                    'hotspotUrl': url,
                    'hotspotAtv': atv,
                    'hotspotAth': ath,
                    'hotspotDistorted': distorted == true ? 1 : 0
                }),
                headers: {'Content-Type': 'application/json'},
                success: function (res) {
                    if (res.code == 1) {
                        // window.location.reload();
                        layer.closeAll();
                        layer.msg("物证添加成功");
                    } else {
                        layer.msg("请求失败");
                    }
                }
            });
        }
    });
}