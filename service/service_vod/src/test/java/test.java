import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.vodtest.InitObject;
import org.junit.Test;

import java.util.List;

public class test {
    @Test
   public void test() throws ClientException {
     DefaultAcsClient client = InitObject.initVodClient("LTAI5tL83KWV8bGHm5K1K2Ps", "2cKT1qgAesiYa2v28rVCNBqwVT3eP6");

     GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        request.setVideoId("1a36f9a08dde71edbfd56723b78e0102");

     response = client.getAcsResponse(request);
        System.out.println(response.getPlayAuth());
    }

    @Test
    public void test2() throws ClientException {
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tL83KWV8bGHm5K1K2Ps", "2cKT1qgAesiYa2v28rVCNBqwVT3eP6");

        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        request.setVideoId("3c8fdbc08e4271edbfd50764a0ec0102");
//        request.setVideoId("1a36f9a08dde71edbfd56723b78e0102");
//        3c8fdbc08e4271edbfd50764a0ec0102

        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
                for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
    }
    @Test
    public void test3(){
        String accessKeyId = "LTAI5tL83KWV8bGHm5K1K2Ps";
        String accessKeySecret = "2cKT1qgAesiYa2v28rVCNBqwVT3eP6";

        String title = "6 - What If I Want to Move Faster - upload by sdk";   //上传之后文件名称
        String fileName = "C:\\Users\\15720586534\\Desktop\\demo.mp4";  //本地文件路径和名称
        //上传视频的方法
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
 }
