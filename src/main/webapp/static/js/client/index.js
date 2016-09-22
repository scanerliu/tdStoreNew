/**
 * 获取楼层广告
 * @param cell 楼层
 * @param fid 楼层id
 */
function getFloorads(cell,fid){
	var url = basePath + "/floorads";
	var loadData = {"floorId":fid};
	$("#floorcontent"+cell).load(url,loadData);
}