package com.mupper.personlist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mupper.personlist.R
import com.mupper.personlist.databinding.ItemPersonBinding
import com.mupper.personlist.presentation.entity.UIPerson

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

class PersonsAdapter : ListAdapter<UIPerson, PersonViewHolder>(DiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = parent.inflate(R.layout.item_person, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val uiPerson = getItem(position)
        holder bind uiPerson
    }
}

class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: ItemPersonBinding = ItemPersonBinding.bind(view)
    infix fun bind(uiPerson: UIPerson) {
        binding bind uiPerson
    }

    private infix fun ItemPersonBinding.bind(uiPerson: UIPerson) {
        with(uiPerson) {
            acronymTextView.text = acronym
            personFullNameTextView.text = fullName
            birthdayTextView.text = birthday
        }
    }
}

object DiffUtilCallback : DiffUtil.ItemCallback<UIPerson>() {
    override fun areItemsTheSame(oldItem: UIPerson, newItem: UIPerson): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UIPerson, newItem: UIPerson): Boolean =
        oldItem == newItem
}