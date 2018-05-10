package com.example.android.meridian.sos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.EditText;

public class InsertMessageContentDialog {
    private AlertDialog dialog;
    private EditText messageText;
    private Context context;
    
    public InsertMessageContentDialog(Context context) {
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        messageText = new EditText(context);
        messageText.setHint("EG: I'm in desperate need of help, please come ASAP!");        
        
        dialog = builder.setTitle("Insert SMS text")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();                   
                    }
                })
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveMessage();
                        dialog.dismiss();
                    }
                })
                .setView(messageText)
                .create();
    }

    private void saveMessage() {
        String text = messageText.getText().toString();
        if (!text.equals("") && text != null) {
            SharedPreferences settings = context.getSharedPreferences(SOSActivity.SETTINGS_FILE, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("messageText", text);
            editor.commit();
        }

    }

    public void show(){
        dialog.show();
    }
}
