package com.sudhan.synupproject.customization.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.sudhan.synupproject.R
import com.sudhan.synupproject.customization.presenter.ICustomizationPresenter
import com.sudhan.synupproject.splash.model.ExcludeModel
import com.sudhan.synupproject.splash.model.VariantGroupModel
import android.widget.RadioButton

class VariantAdapter(var context:Context, var variantList:List<VariantGroupModel>, var iCustomizationPresenter: ICustomizationPresenter) : RecyclerView.Adapter<VariantAdapter.Companion.MyViewHolder>() {
    var selectedHashMap = HashMap<String, HashMap<String, ArrayList<ExcludeModel>>>()
    var count = 0
    companion object {
        class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view)
        {
            var txtVariant: TextView
            var radioGroup: RadioGroup
            init {
                txtVariant = view.findViewById(R.id.txt_variant_name)
                radioGroup = view.findViewById(R.id.radio_group)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.variant_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return variantList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val variantGroup = variantList.get(position)
        holder.txtVariant.text = variantGroup.name

        holder.radioGroup.removeAllViews()
        for (i in 0..variantGroup.variationsList.size-1) {
            if(variantGroup.variationsList.get(i).inStock == 1) {
                val radioVaritionItem = RadioButton(context)
                radioVaritionItem.id = variantGroup.variationsList.get(i).id
                radioVaritionItem.text = variantGroup.variationsList.get(i).name
                if (variantGroup.variationsList.get(i).default == 1) {
                    radioVaritionItem.isChecked = true
                    variantGroup.variationsList.get(i).default = 0
                }
                if(!variantGroup.variationsList.get(i).isEnabled) {
                    radioVaritionItem.isEnabled = false
                    variantGroup.variationsList.get(i).isEnabled = true
                }
                holder.radioGroup.addView(radioVaritionItem)
            }
        }

        holder.radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->

                val groupKey = "${variantGroup.groupId}"
                selectedHashMap.put(groupKey, iCustomizationPresenter.onGetExclusionMap(variantGroup.groupId, checkedId))

                iCustomizationPresenter.onNotifyAdapter(selectedHashMap)
            })
    }
}