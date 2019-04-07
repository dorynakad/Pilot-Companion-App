package  com.juliachihata.co_pilot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DictionaryActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        ListView list = (ListView) findViewById(R.id.theList);

        EditText theFilter = (EditText) findViewById(R.id.searchFilter);
        Log.d(TAG, "onCreate: Started.");

        ArrayList<String> names = new ArrayList<>();
        names.add("AAE                          above aerodrome elevation");
        names.add("ACC                                         area control centre");
        names.add("AD                                    airworthiness directive");
        names.add("ADF                             automatic direction finder");
        names.add("ADIZ                   air defence identification zone");
        names.add("AGL                                         above ground level");
        names.add("AME                    aircraft maintenance engineer");
        names.add("APU                                        auxiliary power unit");
        names.add("ASDA           accelerate stop distance available");
        names.add("ASI                                            airspeed indicator");
        names.add("ASL                                               above sea level");
        names.add("ASR                              airport surveillance radar");
        names.add("ATC                                             air traffic control");
        names.add("ATF                         aerodrome traffic frequency");
        names.add("ATPL                     airline transport pilot licence");
        names.add("ATS                                           air traffic services");
        names.add("BC                                                       back course");
        names.add("BFL                                      balanced field length");
        names.add("C of A                       certificate of airworthiness");
        names.add("C of R                           certificate of registration");
        names.add("CAME              Civil Aviation Medical Examiner");
        names.add("CAP                                             Canada Air Pilot");
        names.add("CARs                 Canadian Aviation Regulations");
        names.add("CARS        community aerodrome radio station");
        names.add("CAS                                        calibrated airspeed");
        names.add("CAT                                        clear air turbulence");
        names.add("CDI                             course deviation indicator");
        names.add("CFI                                       chief flying instructor");
        names.add("CFS                           Canada Flight Supplement");
        names.add("CG                                               centre of gravity");
        names.add("CP                                                      critical point");
        names.add("CPL                              commercial pilot licence");
        names.add("CRFI               Canadian Runway Friction Index");
        names.add("CVFR                      controlled visual flight rules");
        names.add("CVR                                  cockpit voice recorder");
        names.add("CZ                                                      control zone");
        names.add("DF                                                  direction finder");
        names.add("DH                                                 decision height");
        names.add("DMR                  distance measuring equipment");
        names.add("DR                             dead reckoning navigation");
        names.add("DVFR                          defence visual flight rules");
        names.add("EAT                                expected approach time");
        names.add("EFC                  expected further clearance time");
        names.add("EGT                              exhaust gas temperature");
        names.add("ELT                     emergency locator transmitter");
        names.add("EPR                                     engine pressure ratio");
        names.add("ETA                               estimated time of arrival");
        names.add("ETD                         estimated time of departure");
        names.add("EWH                                      eye-to-wheel height");
        names.add("FAA                  Federal Aviation Administration");
        names.add("FDR                                        flight data recorder");
        names.add("FIR                                flight information region");
        names.add("FL                                                           flight level");
        names.add("FMS                          flight management system");
        names.add("FPD                            freezing point depressant");
        names.add("fpm                                               feet per minute");
        names.add("GFA                                   graphic area forecast");
        names.add("GNSS           global navigation satellite system");
        names.add("GP                                                          glide path");
        names.add("gph                                           gallon(s) per hour");
        names.add("GPS                            global positioning system");
        names.add("HAA                              height above aerodrome");
        names.add("HAT                              height above touchdown");
        names.add("HF                                                   high frequency");
        names.add("HI chart                   Enroute High Altitude Chart");
        names.add("HPa                                                    hectopascal");
        names.add("HSI                        horizontal situation indicator");
        names.add("IAF                                           initial approach fix");
        names.add("IAS                                           indicated airspeed");
        names.add("IFR                                     instrument flight rules");
        names.add("ILS                             instrument landing system");
        names.add("IMC          instrument meteorological condition");
        names.add("imp.                                                            imperial");
        names.add("INS                              inertial navigation system");
        names.add("ISA             International Standard Atmosphere");
        names.add("KIAS                            knots indicated air speed");
        names.add("kt                                                                      knot");
        names.add("LAHSO             land and hold short operations");
        names.add("L/D                                          ratio lift/drag ratio");
        names.add("LDA                            landing distance available");
        names.add("LF/MF         low frequency/medium frequency");
        names.add("LO chart                  Enroute Low Altitude Chart");
        names.add("M                                                             magnetic");
        names.add("MAC                          mean aerodynamic chord");
        names.add("MAP                               missed approach point");
        names.add("mb                                                              millibar");
        names.add("MDA                          minimum descent altitude");
        names.add("MEA                          minimum en route altitude");
        names.add("MEL                              minimum equipment list");
        names.add("METAR             aviation routine weather report");
        names.add("MF                                      mandatory frequency");
        names.add("MP                                           manifold pressure");
        names.add("MRA                        minimum reception altitude");
        names.add("MSL                                               mean sea level");
        names.add("MVFR                        marginal visual flight rules");
        names.add("NAT                                                 North Atlantic");
        names.add("NAVAID                                          navigation aid");
        names.add("NDB                                non-directional beacon");
        names.add("NM                                                    nautical mile");
        names.add("NORDO                                                     no radio");
        names.add("NOTAM                                      notice to airmen");
        names.add("OAT                                outside air temperature");
        names.add("OBS                                    omnibearing selector");
        names.add("PAPI             precision approach path indicator");
        names.add("PAR                             precision approach radar");
        names.add("PIC                                           pilot-in-command");
        names.add("PIREP                                   pilot weather report");
        names.add("PNR                                          point of no return");
        names.add("pph                                          pound(s) per hour");
        names.add("PPL                                       private pilot licence");
        names.add("psi                                  pounds per square inch");
        names.add("PSR                           primary surveillance radar");
        names.add("RCC                        rescue co-ordination centre");
        names.add("RCO                  remote communications outlet");
        names.add("RMI                               radio magnetic indicator");
        names.add("RNAV                                           area navigation");
        names.add("RPP                                  recreation pilot permit");
        names.add("RVR                                      runway visual range");
        names.add("RWY                                                            runway");
        names.add("SAE               Society of Automotive Engineers");
        names.add("SELGAL                        selective calling system");
        names.add("SGR                                  specific ground range");
        names.add("SID                    standard instrument departure");
        names.add("SM                                                      statute mile");
        names.add("SSR                      secondary surveillance radar");
        names.add("STAR                           standard terminal arrival");
        names.add("STOL                         short take-off and landing");
        names.add("SVFR                           special visual flight rules");
        names.add("TACAN                      tactical air navigation aid");
        names.add("TAF                                      aerodrome forecast");
        names.add("TAS                                                 true airspeed");
        names.add("TCA                                   terminal control area");
        names.add("TC AIM        Aeronautical Information Manual");
        names.add("TCU                                     terminal control unit");
        names.add("TDZ                                           touchdown zone");
        names.add("T/O                                                             takeoff");
        names.add("TODA                       take-off distance available");
        names.add("TORA                                 take-off run available");
        names.add("TOT                           turbine outlet temperature");



        names.add("UHF                                       ultrahigh frequency");
        names.add("UNICOM                   universal communications");
        names.add("UTC                      Co-Ordinated Universal Time");
        names.add("VDF                                    VHF direction-finding");
        names.add("VFR                                           visual flight rules");
        names.add("VFR OTT                                   VFR over-the-top");
        names.add("VHF                                      very high frequency");
        names.add("VLF                                        very low frequency");
        names.add("VMC              visual meteorological conditions");
        names.add("VNC                                  VFR Navigation Chart");
        names.add("VOR                         VHF omnidirectional range");
        names.add("VORTAC       combination of VOR and TACAN");
        names.add("VOT                             VOR receiver test facility");
        names.add("VSI                                vertical speed indicator");
        names.add("VTA                            VFR Terminal Area Chart");
        names.add("Z                          Co-Ordinated Universal Time");



        adapter = new ArrayAdapter(this, R.layout.dictionary_text_edit, names);
        list.setAdapter(adapter);


        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (DictionaryActivity.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}