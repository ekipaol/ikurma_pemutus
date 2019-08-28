package com.application.bris.brisi_pemutus.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.StringTokenizer;

public class NumberTextWatcherCanNolForThousand implements TextWatcher {

    EditText editText;
    TextView textView;

    public NumberTextWatcherCanNolForThousand() {

    }

    public NumberTextWatcherCanNolForThousand(EditText editText) {
        this.editText = editText;
    }
    public NumberTextWatcherCanNolForThousand( TextView textView) {
        this.textView = textView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try
        {
            if(editText !=null){
                editText.removeTextChangedListener(this);
                String value = editText.getText().toString();


                if (value != null && !value.equals(""))
                {
                    String str = editText.getText().toString().replaceAll(",", "");
                    if (!value.equals(""))
//                    Double.valueOf(str).doubleValue();
                        editText.setText(getDecimalFormat(str));
                    editText.setSelection(editText.getText().toString().length());
                }
                editText.addTextChangedListener(this);
                return;
            }
            else{
                textView.removeTextChangedListener(this);
                String value = textView.getText().toString();


                if (value != null && !value.equals(""))
                {
                    String str = textView.getText().toString().replaceAll(",", "");
                    if (!value.equals(""))
//                    Double.valueOf(str).doubleValue();
                        textView.setText(getDecimalFormat(str));
//                    textView.setText(textView.getText().toString().length());
                }
                textView.addTextChangedListener(this);
                return;
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            editText.addTextChangedListener(this);
        }

    }

    public static String getDecimalFormat(String value)
    {
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1)
        {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }
        String str3 = "";
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt( -1 + str1.length()) == '.')
        {
            j--;
            str3 = ".";
        }
        for (int k = j;; k--)
        {
            if (k < 0)
            {
                if (str2.length() > 0)
                    str3 = str3 + "." + str2;
                return str3;
            }
            if (i == 3)
            {
                str3 = "," + str3;
                i = 0;
            }
            str3 = str1.charAt(k) + str3;
            i++;
        }

    }


    //Trims all the comma of the string and returns
    public static String trimCommaOfString(String string) {
//        String returnString;
        if(string.contains(",")){
            return string.replace(",","");}
        else {
            return string;
        }

    }
}