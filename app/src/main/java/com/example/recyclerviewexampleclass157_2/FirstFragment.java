package com.example.recyclerviewexampleclass157_2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recyclerviewexampleclass157_2.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;
                                            // 21. Implementamos la interface del adapter
                                            // no olvidar implementar el método.
public class FirstFragment extends Fragment implements WordAdapter.InterfacePasarElemento {

    private FragmentFirstBinding mBinding;
    private List<String> dataList = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        mBinding = FragmentFirstBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Instanciamos el adapter y le pasamos el listado de Datos con el metodo setData.
                                            //23. Pasamos el valor this a la instanciación del adapter.
        WordAdapter adapter = new WordAdapter(setData(),this);
        //Le pasamos el adapter al Recycler View
        mBinding.recyclerView.setAdapter(adapter);
        // Le indicamos al RV como mostrar los elementos, podria GridLayout o StaggeredLayout.
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mBinding.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Añado una palabra al listado.
                dataList.add("PALABRA   : " + dataList.size());
                //Notificar al adaptador que insertamos datos
                mBinding.recyclerView.getAdapter().notifyItemInserted(dataList.size());
                //Scroll al final de la lista.
                mBinding.recyclerView.smoothScrollToPosition(dataList.size());
            }
        });
    }

    private List<String> setData() {
        for (int i = 1; i < 101; i++) {
            dataList.add("PALABRA  : " + i);
        }
        return dataList;
    }

    //22. Metodo implementado de la interface.
    @Override
    public void passElement(String item) {
        // 24. Con bundle y la biblioteca de navegacion pasamos el elemento al segundo fragmento.
        Bundle bundle = new Bundle();
        bundle.putString("item", item);
        Navigation.findNavController(mBinding.getRoot())
                .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }
}