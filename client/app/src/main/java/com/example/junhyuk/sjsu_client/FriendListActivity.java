package com.example.junhyuk.sjsu_client;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FriendListActivity extends AppCompatActivity {

    private String url;
    private JSONArray friendList;
    private String alpha;
    private boolean selectFlag;
    private LinearLayout innerLayout;
    private LinearLayout contactList;
    private LinearLayout checkList;
    private ArrayList<MyLinearLayout> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        ImageButton nextBtn = findViewById(R.id.addBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendListActivity.this, HostGameInitActivity.class);
                startActivity(intent);
                finish();

            }
        });

        // set values
        selectFlag = false;
        alpha = " ";
        url = "http://seslab.sejong.ac.kr:7777/getFriend.php?user=1";
        innerLayout = findViewById(R.id.innerLayout);
        contacts = new ArrayList<MyLinearLayout>();

        // add linear layout into innerlayout
        contactList = new LinearLayout(this);
        contactList.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contactList.setOrientation(LinearLayout.VERTICAL);
        contactList.setX(0f);

        checkList = new LinearLayout(this);
        checkList.setOrientation(LinearLayout.VERTICAL);
        checkList.setVisibility(LinearLayout.GONE);
        checkList.setLayoutParams(new LinearLayout.LayoutParams(272, ViewGroup.LayoutParams.MATCH_PARENT));


        // add view point
        innerLayout.addView(checkList);
        innerLayout.addView(contactList);


        findViewById(R.id.selectBtn).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (selectFlag) {   // redo select

                            ObjectAnimator animator = ObjectAnimator.ofFloat(contactList, "translationX", 0f);
                            animator.setDuration(200);
                            checkList.setVisibility(LinearLayout.GONE);
                            contactList.setTranslationX(272f);
                            animator.start();
                            checkList.setVisibility(LinearLayout.GONE);
                            for (MyLinearLayout layout :
                                    contacts) {
                                layout.unCheck();

                            }
                            ((Button)findViewById(R.id.selectBtn)).setText("SELECT");
                            selectFlag = !selectFlag;
                        } else {    // set to select
                            ObjectAnimator animator = ObjectAnimator.ofFloat(contactList, "translationX", 272f);
                            animator.setDuration(200);
                            animator.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    checkList.setVisibility(LinearLayout.VISIBLE);
                                    contactList.setTranslationX(0f);
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                            animator.start();
                            ((Button)findViewById(R.id.selectBtn)).setText("CANCEL");
                            selectFlag = !selectFlag;
                        }
                    }
                }
        );


        ServerConnect getFriends = new ServerConnect();
        try {
            String resp = getFriends.execute(url).get();
            friendList = new JSONArray(resp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray sortedJsonArray = new JSONArray();

        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < friendList.length(); i++) {
            try {
                jsonValues.add(friendList.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort( jsonValues, new Comparator<JSONObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private static final String KEY_NAME = "fname";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(KEY_NAME);
                    valB = (String) b.get(KEY_NAME);
                }
                catch (JSONException e) {
                    //do something
                }

                return valA.compareTo(valB);
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < friendList.length(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }

        for (int i=0; i<sortedJsonArray.length(); i++) {
            try {
                JSONObject friend = sortedJsonArray.getJSONObject(i);
                if (friend.getInt("status") != 0) {
                    continue;
                } else {
                    String name = friend.getString("fname");
                    name += " " + friend.getString("lname");
                    // If alphabet change
                    String temp = friend.getString("fname").substring(0,1);
                    if (!alpha.equals(temp)) {
                        addSplit(contactList, temp);
                        addCheck(checkList, false, null);
                    }

                    // Add contact
                    MyLinearLayout tmp = addContact(contactList, friend.getInt("fidx"), name, friend.getString("imgUrl"));
                    addCheck(checkList, true, tmp.getView());
                    contacts.add(tmp);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public MyLinearLayout addContact(LinearLayout target, int fidx, String name, String imgUrl) {
        MyLinearLayout innerContact = new MyLinearLayout(this);
        innerContact.setOrientation(LinearLayout.HORIZONTAL);
        innerContact.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        innerContact.setBackground(getDrawable(R.drawable.divider));
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) innerContact.getLayoutParams();
        lp.setMargins(0,0,0,0);

        ImageView profileImg = new ImageView(this);
        new DownloadImageTask(profileImg).execute(imgUrl);
        profileImg.setLayoutParams(new LinearLayout.LayoutParams(192, 192));
        lp = (LinearLayout.LayoutParams) profileImg.getLayoutParams();
        lp.setMargins(64,40,64,40);

        TextView profileName = new TextView(this);
        profileName.setText(name);
        profileName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        profileName.setGravity(Gravity.CENTER_VERTICAL);
        profileName.setTextSize(16f);
        profileName.setPadding(0,0,0,0);

        innerContact.addView(profileImg);
        innerContact.addView(profileName);
        innerContact.setCheckImageView(new TagImageView(this));
        innerContact.setFidx(fidx);


        target.addView(innerContact);

        return innerContact;
    }

    public void addCheck(LinearLayout target, Boolean flag, @Nullable TagImageView view) {
        if (flag) {
            // add check box
            target.addView(view);
        } else {
            // add line splitter
            TextView line = new TextView(this);
            line.setText("");
            line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 135));

            target.addView(line);
        }
    }

    public void addSplit(LinearLayout target, String alpha) {
        TextView line = new TextView(this);
        line.setText(alpha);
        line.setTextColor(0xFF367AFD);
        line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,135));
        line.setBackground(getDrawable(R.drawable.divider));
        line.setGravity(Gravity.CENTER_VERTICAL);
        line.setPadding(90,0,0,0);

        target.addView(line);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private class TagImageView extends android.support.v7.widget.AppCompatImageView {
        private int id;

        public TagImageView(Context context) {
            super(context);
            this.setLayoutParams(new ViewGroup.LayoutParams(272, 272));
            this.setImageResource(R.drawable.bgldpi);
            this.setPadding(78,78,78,78);

            this.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (id == R.drawable.bgldpi) {
                        setImageResource(R.drawable.iconldpi);
                    } else {
                        setImageResource(R.drawable.bgldpi);
                    }
                }
            });
        }

        @Override
        public void setImageResource(int resId) {
            super.setImageResource(resId);
            this.id = resId;
        }

        public int getImageResource() {
            return this.id;
        }
    }

    private class MyLinearLayout extends LinearLayout {
        TagImageView view;
        int fidx;

        public MyLinearLayout(Context context) {
            super(context);
        }

        public void setCheckImageView(TagImageView view) {
            this.view = view;
        }

        public TagImageView getView() {
            return view;
        }

        public void setFidx(int fidx) {
            this.fidx = fidx;
        }

        public int getFidx() {
            return fidx;
        }

        public boolean isChecked() {
            return this.view.getImageResource() == R.drawable.iconldpi;
        }

        public void unCheck() {
            this.view.setImageResource(R.drawable.bgldpi);
        }
    }
}
