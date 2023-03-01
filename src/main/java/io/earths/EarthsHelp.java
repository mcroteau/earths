package io.earths;

import io.earths.model.Need;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EarthsHelp {

    public String getUserMaintenance(){ return "users::maintenance::";}

    public String getSuper(){ return "super"; }
    public String getSaint(){ return "saint"; }
    public String getRemarkable(){ return "remarkable"; }
    public String getWorker(){ return "worker"; }
    public String getPatron(){ return "patron"; }

    public String getSuperUsername(){ return "croteau.mike@gmail.com"; }
    public String getSuperPassword(){ return "extraordinary"; }

    public String getDateFormat(){ return "yyyyMMddHHmm"; }
    public String getDatePretty(){ return "HH:mmaa dd MMM"; }

    public Integer getIdIdx(){ return 0; }
    public Integer getMailingIdx(){ return 1; }
    public Integer getPlaceIdx(){ return 2; }
    public Integer getPhoneIdx(){ return 3; }
    public Integer getJobIdx(){ return 4; }
    public Integer getLaptopIdx(){ return 5; }
    public Integer getInternetIdx(){ return 6; }

    public String[] needs = {
            "Id card",
            "Mailing address",
            "Place to live",
            "Phone",
            "Job",
            "Laptop/Computer",
            "Internet"
    };

    public String getNeed(int idx){
        String[] needs = getNeeds();
        return needs[idx];
    }

    public String[] getNeeds(){
        return new String[]{
                "Id card",
                "Mailing address",
                "Place to live",
                "Phone",
                "Job",
                "Laptop/Computer",
                "Internet"
        };
    }

    public String getDefault(){
        String[] needsGo = getNeeds();
        String needs = new String();
        int idx = 0;
        for(String need : needsGo){
            needs += "no";
            if(idx < needsGo.length){
                idx++;
                needs+=":";
            }
        }
        return needs;
    }

    public List<Need> getNeeds(String needsDelimeter){
        List<Need> needsGo = new ArrayList<>();
        String[] needsPre = needsDelimeter.split(":");
        int idx = 0;
        for(String need : needsPre){
            needsGo.add(new Need(need, getNeed(idx)));idx++;
        }
        return needsGo;
    }

    public String toUri(String alskdjlakjsd){
        String uri = alskdjlakjsd.toLowerCase();
        return uri.replaceAll("\\s", "-");
    }

    public String getPhone(String phone){
        if(phone != null)
            return phone
                    .replaceAll("[^a-zA-Z0-9]", "")
                    .replaceAll(" ", "")
                    .replaceAll(" ", "")
                    .replaceAll(" ", "");
        return "";
    }

    public String getSpaces(String email) {
        if(email != null)
            return email.replaceAll(" ", "")
                    .replaceAll(" ", "")
                    .replaceAll(" ", "");
        return "";
    }

    public String getExt(final String path) {
        String result = null;
        if (path != null) {
            result = "";
            if (path.lastIndexOf('.') != -1) {
                result = path.substring(path.lastIndexOf('.'));
                if (result.startsWith(".")) {
                    result = result.substring(1);
                }
            }
        }
        return result;
    }

    public boolean isValidMailbox(String str){
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(str);
    }

    public int getNumber(int max){
        Random r = new Random();
        return r.nextInt(max);
    }

    public boolean containsSpecialCharacters(String str) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.find();
    }

    private String getExtension(final String path) {
        String result = null;
        if (path != null) {
            result = "";
            if (path.lastIndexOf('.') != -1) {
                result = path.substring(path.lastIndexOf('.'));
                if (result.startsWith(".")) {
                    result = result.substring(1);
                }
            }
        }
        return result;
    }

    public String getUid(int n) {
        String CHARS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        StringBuilder uuid = new StringBuilder();
        int divisor = n/6;
        Random rnd = new Random();
        for(int z = 0; z < n;  z++) {
            if( z % divisor == 0 && z > 0) {
                uuid.append("-");
            }
            int index = (int) (rnd.nextFloat() * CHARS.length());
            uuid.append(CHARS.charAt(index));
        }
        return uuid.toString();
    }

    public String getString(int n) {
        String CHARS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
        StringBuilder guid = new StringBuilder();
        int divisor = n/3;
        Random rnd = new Random();
        for(int z = 0; z < n;  z++) {
            if( z % divisor == 0 && z > 0) {
                guid.append("-");
            }
            int index = (int) (rnd.nextFloat() * CHARS.length());
            guid.append(CHARS.charAt(index));
        }
        int index = (int) (rnd.nextFloat() * CHARS.length());
        guid.append(CHARS.charAt(index));

        return guid.toString();
    }

    public String getGift(int n) {
        String CHARS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
        StringBuilder guid = new StringBuilder();
        int divisor = n/4;
        Random rnd = new Random();
        for(int z = 0; z < n;  z++) {
            if( z % divisor == 0 && z > 0) {
                guid.append("-");
            }
            int index = (int) (rnd.nextFloat() * CHARS.length());
            guid.append(CHARS.charAt(index));
        }
        int index = (int) (rnd.nextFloat() * CHARS.length());
        guid.append(CHARS.charAt(index));

        return guid.toString();
    }

    public String getRef(int n) {
        String CHARS = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder uuid = new StringBuilder();
        int divisor = n/2;
        Random rnd = new Random();
        for(int z = 0; z < n;  z++) {
            if( z % divisor == 0 && z > 0) {
                uuid.append("-");
            }
            int index = (int) (rnd.nextFloat() * CHARS.length());
            uuid.append(CHARS.charAt(index));
        }
        int index = (int) (rnd.nextFloat() * CHARS.length());
        uuid.append(CHARS.charAt(index));

        return uuid.toString();
    }

    public long getTime(){
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(getDateFormat());
        String date = dtf.format(ldt);
        return Long.parseLong(date);
    }

    public long getDateTimezone(String timezone){
        LocalDateTime ldt = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        ZoneOffset zoneOffset = zone.getRules().getOffset(ldt);
        ZonedDateTime zdt = ldt.atOffset(zoneOffset)
                .atZoneSameInstant(ZoneId.of(timezone));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(getDateFormat());
        String date = dtf.format(zdt);
        return Long.parseLong(date);
    }

    public long getDateTimezoneMins(int mins, String timezone){
        LocalDateTime ldt = LocalDateTime.now().plusMinutes(mins);
        ZoneId zone = ZoneId.systemDefault();
        ZoneOffset zoneOffset = zone.getRules().getOffset(ldt);
        ZonedDateTime zdt = ldt.atOffset(zoneOffset)
                .atZoneSameInstant(ZoneId.of(timezone));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(getDateFormat());
        String date = dtf.format(zdt);
        return Long.parseLong(date);
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String getPretty(Long date){
        String dateString = "";
        try {
            SimpleDateFormat parser = new SimpleDateFormat(getDateFormat());
            Date d = parser.parse(Long.toString(date));

            SimpleDateFormat sdf2 = new SimpleDateFormat(getDatePretty());
            dateString = sdf2.format(d);
        }catch(Exception ex){}
        return dateString;
    }

    public String pad(String value, int places, String character){
        if(value.length() < places){
            value = character.concat(value);
            pad(value, places, character);
        }
        return value;
    }


    public String getEncodedPrefix(String name) {
        String ext = getExt(name);
        if(ext.equals("jpeg"))return "data:image/jpeg;base64,";
        if(ext.equals("jpg"))return "data:image/jpeg;base64,";
        if(ext.equals("png"))return "data:image/png;base64,";
        if(ext.equals("gif"))return "data:image/gif;base64,";
        return "";
    }

    public String getEncoded(String photoPath) {
        try {
            File file = new File(photoPath);
            FileInputStream stream = new FileInputStream(file);

            int bufLength = 2048;
            byte[] buffer = new byte[2048];
            byte[] data;

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int readLength;
            while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
                out.write(buffer, 0, readLength);
            }

            data = out.toByteArray();

            out.close();
            stream.close();

            return Base64.getEncoder().withoutPadding().encodeToString(data);

        }catch(IOException ioex){
            ioex.printStackTrace();
        }
        return "";
    }

}



