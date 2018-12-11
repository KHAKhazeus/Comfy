package com.kha.cbc.comfy.view.team.grouptrack

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.amap.api.maps.AMap
import com.kha.cbc.comfy.R
import com.kha.cbc.comfy.view.common.yum
import kotlinx.android.synthetic.main.activity_group_track.*
import com.amap.api.track.AMapTrackClient
import com.amap.api.track.ErrorCode
import com.amap.api.track.OnTrackLifecycleListener
import com.amap.api.track.TrackParam
import com.amap.api.track.query.model.*
import com.kha.cbc.comfy.BuildConfig
import com.kha.cbc.comfy.model.User


class GroupTrackActivity : AppCompatActivity() {


    //TODO: 开启定位/蓝牙？

    val RC_PERMISSIONS = 1

    lateinit var map: AMap

    val aMapTrackClient = AMapTrackClient(applicationContext)

    val onTrackLifecycleListener = object: OnTrackLifecycleListener(){
        override fun onStartGatherCallback(p0: Int, p1: String?) {
            if (p0 == ErrorCode.TrackListen.START_GATHER_SUCEE ||
                p0 == ErrorCode.TrackListen.START_GATHER_ALREADY_STARTED) {
                group_track_layout.yum("定位采集开启成功！").show()
            } else {
                group_track_layout.yum("定位采集启动异常，").show()
            }
        }

        override fun onStopTrackCallback(p0: Int, p1: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindServiceCallback(p0: Int, p1: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onStopGatherCallback(p0: Int, p1: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onStartTrackCallback(p0: Int, p1: String?) {
            if (p0 == ErrorCode.TrackListen.START_TRACK_SUCEE ||
                p0 == ErrorCode.TrackListen.START_TRACK_SUCEE_NO_NETWORK ||
                p0 == ErrorCode.TrackListen.START_TRACK_ALREADY_STARTED) {
                // 服务启动成功，继续开启收集上报
                aMapTrackClient.startGather(this)
            } else {
                group_track_layout.yum("轨迹上报服务服务启动异常，").show()
            }
        }
    }

    private fun initService(){
        map = group_track_amap.map
        aMapTrackClient.queryTerminal(QueryTerminalRequest(BuildConfig.COMFYGROUPTRACKSERVICEID.toLong(),
            User.comfyUserObjectId), object: OnTrackListener {

            override fun onLatestPointCallback(p0: LatestPointResponse?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCreateTerminalCallback(p0: AddTerminalResponse?) {
                if(p0 != null && p0.isSuccess){
                    if(p0.tid <= 0){
                        //terminal 不存在，先创建
                        aMapTrackClient.addTerminal(AddTerminalRequest(User.comfyUserObjectId,
                            BuildConfig.COMFYGROUPTRACKSERVICEID.toLong()), object: OnTrackListener{
                            override fun onLatestPointCallback(p0: LatestPointResponse?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onCreateTerminalCallback(p0: AddTerminalResponse?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onQueryTrackCallback(p0: QueryTrackResponse?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onDistanceCallback(p0: DistanceResponse?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onQueryTerminalCallback(p0: QueryTerminalResponse?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onHistoryTrackCallback(p0: HistoryTrackResponse?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onParamErrorCallback(p0: ParamErrorResponse?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onAddTrackCallback(p0: AddTrackResponse?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }
                        })
                    }
                    else{
                        val lastTerminalId = p0.tid
                        aMapTrackClient.startTrack(TrackParam(BuildConfig.COMFYGROUPTRACKSERVICEID.toLong(),
                            lastTerminalId),
                            onTrackLifecycleListener)
                    }
                }
                else{
                    //请求失败
                    group_track_layout.yum("请求失败." + p0.errorMsg).show()
                }
            }

            override fun onQueryTrackCallback(p0: QueryTrackResponse?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDistanceCallback(p0: DistanceResponse?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTerminalCallback(p0: QueryTerminalResponse?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onHistoryTrackCallback(p0: HistoryTrackResponse?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onParamErrorCallback(p0: ParamErrorResponse?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAddTrackCallback(p0: AddTrackResponse?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        group_track_amap.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        group_track_amap.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        group_track_amap.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        group_track_amap.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_track)
        group_track_amap.onCreate(savedInstanceState)

        if (
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE
            )
                ,RC_PERMISSIONS)
        }
        else{
            initService()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            RC_PERMISSIONS -> {
                var isPermitted = true
                for(permission in permissions){
                    if(grantResults[permissions.indexOf(permission)] != PackageManager.PERMISSION_GRANTED){
                        isPermitted = false
                        group_track_layout.yum("You Denied the permission").show()
                        break
                    }
                }
                if(isPermitted){
                    initService()
                }
            }
        }
    }
}
