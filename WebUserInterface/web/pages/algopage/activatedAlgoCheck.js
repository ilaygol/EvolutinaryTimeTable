
$(function() {
    $.ajax({
        url: "progressUpdate",
        timeout: 1000,
        success: function (progressData) {
            if(progressData["m_IsRunningAlgo"])
            {
                labelsUpdate=setInterval(algoProgressUpdate,1000);
                playBtnDisabilityManagement();
            }
        }
    });
})