/*
 * GoBees
 * Copyright (c) 2016 - 2017 David Miguel Lozano
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/gpl-3.0.txt>.
 */

package com.davidmiguel.gobees.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.davidmiguel.gobees.Injection;
import com.davidmiguel.gobees.R;
import com.davidmiguel.gobees.data.source.repository.GoBeesRepository;
import com.davidmiguel.gobees.utils.AndroidUtils;

/**
 * Presents a set of application settings presented as a single list.
 */
public class SettingsActivity extends AppCompatActivity {

    private GoBeesRepository goBeesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_act);

        // Set up the toolbar
        AndroidUtils.setUpToolbar(this, false, R.string.settings_toolbar_title);

        // Add fragment to the activity
        SettingsFragment settingsFragment = SettingsFragment.newInstance();
        getFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, settingsFragment)
                .commit();

        // Init db
        goBeesRepository = Injection.provideApiariesRepository();
        goBeesRepository.openDb();

        // Create the presenter
        new SettingsPresenter(goBeesRepository, settingsFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close database
        goBeesRepository.closeDb();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
