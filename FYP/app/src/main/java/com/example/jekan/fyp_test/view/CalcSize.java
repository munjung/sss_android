package com.example.jekan.fyp_test.view;

import com.example.jekan.fyp_test.view.DotPoint;

import java.util.ArrayList;

/**
 * Created by jekan on 2018-02-21.
 */

//가슴, 허리, 엉덩이, 허벅지 너비 메소드 작성
//Point클래스 -> DotPoint 이름 변경
// 사이즈 계산 클래스
public class CalcSize {
    private float actual_height;
    private float height;
    private ArrayList<DotPoint> fPoints, sPoints;
    private float ratio;

    // 배열 점 저장하는 순서 중요함 (정면: [0]어깨, [1]겨드랑이, [2]가슴, [3]손목, [4]허리, [5]엉덩이, [6]사타구니, [7]발목)
    // 배열 점 저장하는 순서 중요함 (측면: [0]머리, [1]옆가슴, [2]등, [3]앞허리, [4]뒷허리, [5]앞엉덩이, [6]뒷엉덩이, [7]발바닥)
    public CalcSize(ArrayList<DotPoint> fPoints, ArrayList<DotPoint> sPoints, float actual_height) {
        this.fPoints = fPoints;
        this.sPoints = sPoints;
        this.actual_height = actual_height;
    }

    // 키, ratio 계산
    public float clacHeightPixel() {
        /*
        DotPoint s_head = sPoints.get(0); // 옆 머리
        DotPoint s_foot = sPoints.get(7); // 옆 발바닥
        height = s_foot.getPointY() - s_head.getPointY(); // 키에 해당하는 픽셀 수
        this.ratio =actual_height / height; // ratio값 계산*/

        height = getPointDistance(sPoints.get(0), sPoints.get(7));
        this.ratio = actual_height/height;
        return ratio;
    }

    // 어깨 너비
    public float getShoulderWidth() {
     /* DotPoint f_shoulder = fPoints.get(0);
        DotPoint f_groin = fPoints.get(6);
        float width = (f_groin.getPointX() - f_shoulder.getPointX()) * 2;
        return width;*/
     return  getPointDistance(fPoints.get(0), fPoints.get(6))*2*ratio;
    }

    // 팔 길이 (거리 공식) {
    public float getArmLength() {
      /* DotPoint f_shoulder = fPoints.get(0);
        DotPoint f_wrist = fPoints.get(3);
        float length = 0;
        return length;*/
      return getPointDistance(fPoints.get(0), fPoints.get(3))*ratio;
    }

    //가슴 너비(둘레x)
    public float getChestWidth(){
        return getPointDistance(fPoints.get(2), fPoints.get(6))*2;
    }

    //허리
    public float getWaistWidth(){
        return  getPointDistance(fPoints.get(4), fPoints.get(6))*2;

    }

    //엉덩이
    public float getHipWidth(){
        return getPointDistance(fPoints.get(5), fPoints.get(6))*2;
    }

    //허벅지
    public float getThighWidth(){
        return getPointDistance(fPoints.get(5), fPoints.get(6));
    }

    //다리길이 (일단 정면기준으로 작성. 측면에서 재는 것이 맞나 고민!)
    public float getLegLength(){
        return getPointDistance(fPoints.get(4), fPoints.get(7));
    }


    // 점과 점 사이의 거리
    // 항상 직선일리 없으니까 거리공식 쓰는게 좋을듯해!
    // 그런데 그렇게하면 소수점 자리가 엄청 길게 나와서 어디까지 끊을지 생각해보자(DB)
    public float getPointDistance(DotPoint point1, DotPoint point2){

        double pointX = Math.abs(point1.getPointX()-point2.getPointX());
        double pointY = Math.abs(point1.getPointY()-point2.getPointY());
        double distance = Math.sqrt(Math.pow(pointX, 2)+Math.pow(pointY, 2));
        // distance = Math.ceil(distance);
        double num = Math.pow(10.0, 1); //소수 첫번째 자리까지 남기기
        distance = Math.round((distance*num)/num);
        return (float)distance;
    }

}