package com.muzima.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.muzima.api.model.Concept;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.muzima.utils.Constants.CONCEPT_PREF;
import static com.muzima.utils.Constants.CONCEPT_PREF_KEY;

public class PreferenceHelper {
    private Context context;

    public PreferenceHelper(Context context) {
        this.context = context;
    }

    public void addConcepts(List<Concept> concepts) {
        SharedPreferences conceptSharedPreferences = context.getSharedPreferences(CONCEPT_PREF, Context.MODE_PRIVATE);
        Set<String> conceptUuidSet = conceptSharedPreferences.getStringSet(CONCEPT_PREF_KEY, new LinkedHashSet<String>());
        SharedPreferences.Editor editor = conceptSharedPreferences.edit();
        Set<String> copyOfUuidSet = new LinkedHashSet<String>();
        copyOfUuidSet.addAll(conceptUuidSet);
        for (Concept concept : concepts) {
            copyOfUuidSet.add(concept.getUuid());
        }
        editor.putStringSet(CONCEPT_PREF_KEY, copyOfUuidSet);
        editor.commit();
    }
}