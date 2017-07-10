package com.xz.spannablestringbuilderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView mTvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvPrice = (TextView) findViewById(R.id.tv_price);

        int iFenPrice = 5000; // 5000 分
        double yuanPrice = divide(Double.valueOf(iFenPrice), 100.0);
        String strPrice = "¥ " + getFixedTwo(yuanPrice);

        SpannableString spPrice = new SpannableString(strPrice);
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(sp2px(30));

        spPrice.setSpan(sizeSpan,2,spPrice.length()-3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvPrice.setText(spPrice);
    }

    /**
     * sp转px
     *
     * @param spValue sp值
     * @return px值
     */
    private int sp2px(float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, getResources().getDisplayMetrics());
    }

    /**
     * 解决double 的 相除 精度
     *
     * @param divisor
     * @param dividend
     * @return
     */
    private Double divide(Double divisor, Double dividend) {
        BigDecimal bigDivisor = new BigDecimal(divisor.toString());
        BigDecimal bigDividend = new BigDecimal(dividend.toString());

        return bigDivisor.divide(bigDividend, 2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 转化成0.00格式的，如果不是数字，则直接返回
     *
     * @param price
     * @return
     */
    private String getFixedTwo(Double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        String money = df.format(price);
        return money;
    }

    /**
     * 校验 str是否为数字
     *
     * @param str
     * @return
     */
    private boolean isNum(String str) {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }
}
