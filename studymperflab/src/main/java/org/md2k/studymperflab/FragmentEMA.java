package org.md2k.studymperflab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import org.md2k.datakitapi.DataKitAPI;
import org.md2k.datakitapi.datatype.DataTypeString;
import org.md2k.datakitapi.exception.DataKitException;
import org.md2k.datakitapi.time.DateTime;
import org.md2k.studymperflab.ActivityMain;
import org.md2k.studymperflab.R;

import es.dmoral.toasty.Toasty;
import mehdi.sakout.fancybuttons.FancyButton;


public class FragmentEMA extends Fragment {

    View view;
    FancyButton fancyButtonBack;
    ActivityMain activityMain;
    RadioGroup radioGroupStress;
    RadioGroup radioGroupAnger;
    RadioGroup radioGroupHappy;
    RadioGroup radioGroupCheerful;
    RadioGroup radioGroupSad;
    private BootstrapButton buttonSave;
   // private BootstrapButton buttonCancel;
    private BootstrapButton buttonAdd;
    private EditText editTextNote;


    String string_stress_answer;
    String string_anger_answer;
    String string_happy_answer;
    String string_cheerful_answer;
    String string_sad_answer;
    int selected_stress_answer;
    int selected_anger_answer;
    int selected_happy_answer;
    int selected_cheerful_answer;
    int selected_sad_answer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ema, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        activityMain = (ActivityMain) getActivity();
        fancyButtonBack = (FancyButton) view.findViewById(R.id.button_back);

        radioGroupStress = (RadioGroup) view.findViewById(R.id.radio_stress);
        radioGroupAnger = (RadioGroup) view.findViewById(R.id.radio_anger);
        radioGroupHappy = (RadioGroup) view.findViewById(R.id.radio_happy);
        radioGroupCheerful = (RadioGroup) view.findViewById(R.id.radio_cheerful);
        radioGroupSad = (RadioGroup) view.findViewById(R.id.radio_sad);

        buttonSave = (BootstrapButton) view.findViewById(R.id.btn_work_save);
      //  buttonCancel = (BootstrapButton) view.findViewById(R.id.btn_work_cancel);
        fancyButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityMain.started == true) {
                    Toasty.normal(getContext(), "Please stop the session first", Toast.LENGTH_SHORT).show();

                } else
                    activityMain.loadWorkType();
            }
        });
        editTextNote = (EditText) view.findViewById(R.id.editView_notes);
        buttonAdd = (BootstrapButton) view.findViewById(R.id.btn_work_add);

    //    prepareCancel(view);
        prepareSave(view);
        prepareNoteAdd();
    //    enableButtons(true, false, false);


        //   radioGroupTypingTask = (RadioGroup) view.findViewById(R.id.radio_typing_task);


    }
    @Override
    public void onResume(){
        radioGroupStress.clearCheck();
        radioGroupSad.clearCheck();
        radioGroupCheerful.clearCheck();
        radioGroupHappy.clearCheck();
        radioGroupAnger.clearCheck();
        editTextNote.setText("");
        super.onResume();
    }

    void prepareNoteAdd() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextNote.getText() == null || editTextNote.getText().toString().length() == 0)
                    return;
                String work_status = activityMain.workType + "," + "note," + editTextNote.getText().toString();
                editTextNote.setText("");
                try {
                    DataKitAPI.getInstance(getActivity()).insert(((ActivityMain) getActivity()).dataSourceClient, new DataTypeString(DateTime.getDateTime(), work_status));
                } catch (DataKitException e) {
                }
                Toasty.normal(getContext(), "Note added", Toast.LENGTH_SHORT).show();


            }
        });
    }


    void prepareSave(final View view) {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   workAnnotation.work_activity_other=other_activity.getText().toString();
                //  workAnnotation.work_context_other=other_context.getText().toString();
                //  workAnnotation.operation="START";
                //  workAnnotation.timestamp= DateTime.getDateTime();
                //  insertData();
                //  writeSharedPreference();
                //      handler.removeCallbacks(runnable);
                //    handler.post(runnable);
                if (check() == true) {

                    String work_status = activityMain.workType + "," + "stress" + "," + string_stress_answer + "," + "anger" + "," + string_anger_answer + "," + "happy" + "," + string_happy_answer + "," + "cheerful" + "," + string_cheerful_answer + "," + "sad" + "," + string_sad_answer + "," + "saved";
                    activityMain.started = false;
                    try {
                        DataKitAPI.getInstance(getActivity()).insert(((ActivityMain) getActivity()).dataSourceClient, new DataTypeString(DateTime.getDateTime(), work_status));
                    } catch (DataKitException e) {
                    }
                    Toasty.normal(getContext(), work_status, Toast.LENGTH_SHORT).show();
                //TODO
                    //    resetRadio();
                    //enableButtons(true, false, true);
                   // enableButtons(false, true, false);


//                    activityMain.started = true;
//                    String work_status = activityMain.workType + "," + "saved";
//                    Toasty.normal(getContext(), work_status, Toast.LENGTH_SHORT).show();
//                    enableButtons(false, true, false);
//                }


                }
            }
        });
        }






//    private void prepareCancel(View view) {
//        buttonCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //   workAnnotation.operation="CANCEL";
//                //   workAnnotation.timestamp=DateTime.getDateTime();
//                //   insertData();
//                //   writeSharedPreference();
//                //   handler.removeCallbacks(runnable);
//                if (check() == true) {
//                    activityMain.started = false;
//                    String work_status = activityMain.workType + "," + "stress" + "," + string_stress_answer + "," + "anger" + "," + string_anger_answer + "," + "happy" + "," + string_happy_answer + "," + "cheerful" + "," + string_cheerful_answer + "," + "sad" + "," + string_sad_answer + "," + "cancel";
//                    try {
//                        DataKitAPI.getInstance(getActivity()).insert(((ActivityMain) getActivity()).dataSourceClient, new DataTypeString(DateTime.getDateTime(), work_status));
//                    } catch (DataKitException e) {
//                    }
//                    Toasty.normal(getContext(), work_status, Toast.LENGTH_SHORT).show();
//                    enableButtons(true, false, true);
//                }
//                //     showMessage("CANCELLED");
//            }
//        });
//    }


//    void enableButtons(boolean st, boolean sp, boolean wt) {
//        buttonSave.setEnabled(st);
//        buttonSave.setShowOutline(!st);
//
//   //     buttonCancel.setEnabled(sp);
//     //   buttonCancel.setShowOutline(!sp);
//    }

    boolean check() {

//        int selectedId = radioGroupTypingTask.getCheckedRadioButtonId();
        //      int selectedstatus = radioGroupTypingStatus.getCheckedRadioButtonId();
//        if (selectedId == -1) {
//            Toasty.error(getContext(), "Please select a typing type to continue...", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        else if (selectedstatus == -1) {
//            Toasty.error(getContext(), "Please select a typing status to continue...", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        else  {
////            RadioButton r_task = (RadioButton) view.findViewById(selectedId);
//            RadioButton r_status = (RadioButton) view.findViewById(selectedstatus);
//            typing_task = r_task.getText().toString();
//            typing_status=r_status.getText().toString();
//            return true;
//        }
//

        selected_stress_answer = radioGroupStress.getCheckedRadioButtonId();
        selected_anger_answer = radioGroupAnger.getCheckedRadioButtonId();
        selected_happy_answer = radioGroupHappy.getCheckedRadioButtonId();
        selected_cheerful_answer = radioGroupCheerful.getCheckedRadioButtonId();
        selected_sad_answer = radioGroupSad.getCheckedRadioButtonId();

        if (selected_stress_answer == -1) {
            Toasty.error(getContext(), "Please select answer for stress", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selected_anger_answer == -1) {
            Toasty.error(getContext(), "Please select answer for anger/frustration", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selected_happy_answer == -1) {
            Toasty.error(getContext(), "Please select answer for happy", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selected_cheerful_answer == -1) {
            Toasty.error(getContext(), "Please select answer for cheerful", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selected_sad_answer == -1) {
            Toasty.error(getContext(), "Please select answer for sad", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            RadioButton stress_answer = (RadioButton) view.findViewById(selected_stress_answer);
            RadioButton anger_answer = (RadioButton) view.findViewById(selected_anger_answer);
            RadioButton happy_answer = (RadioButton) view.findViewById(selected_happy_answer);
            RadioButton cheerful_answer = (RadioButton) view.findViewById(selected_cheerful_answer);
            RadioButton sad_answer = (RadioButton) view.findViewById(selected_sad_answer);


            string_stress_answer = stress_answer.getText().toString();
            string_anger_answer = anger_answer.getText().toString();
            string_happy_answer = happy_answer.getText().toString();
            string_cheerful_answer = cheerful_answer.getText().toString();
            string_sad_answer = sad_answer.getText().toString();


            return true;
        }
    }
void resetRadio(){
  //  radioGroupStress.setChecked(false);

    }

}
