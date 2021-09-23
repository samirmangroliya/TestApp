package com.samir.testapptexis;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import android.content.Context;

import com.samir.testapptexis.adapters.AccountAdapter;
import com.samir.testapptexis.adapters.TransActionAdapter;
import com.samir.testapptexis.data.model.TransactionModel;

import java.util.ArrayList;
import java.util.Collections;

public class AdapterTest {

    @Mock
    Context context;

    private TransActionAdapter transActionAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addGridItemsToViewNotifiesParentAndAddsItemToTileList() {
        transActionAdapter = spy(new TransActionAdapter(new ArrayList()));

        transActionAdapter.addItemForTest(new TransactionModel("Transaction 1", "test", 125.25));
        verify(transActionAdapter).notifyItemChanged(0);
    }

}