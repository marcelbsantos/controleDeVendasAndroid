package br.com.controledevendas.util;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class Validator {
	
	public static boolean validateNotNull(View view, String msg) {
		if(view instanceof EditText) {
			EditText editText = (EditText) view;
			Editable editable = editText.getText();
			if (editable != null) {
				String strText = editable.toString();
				if(!TextUtils.isEmpty(strText)) {
					return true;
				}
			}
			editText.setError(msg);
			editText.setFocusable(true);
			editText.requestFocus();
			return false;
		}
		return false;
	}

}
