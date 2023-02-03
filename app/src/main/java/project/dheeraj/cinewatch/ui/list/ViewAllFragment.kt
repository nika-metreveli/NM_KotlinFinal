package project.dheeraj.cinewatch.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import project.dheeraj.cinewatch.R
import project.dheeraj.cinewatch.databinding.FragmentViewAllBinding
import project.dheeraj.cinewatch.ui.adapter.BookmarkRecyclerViewAdapter
import project.dheeraj.cinewatch.ui.adapter.ViewAllRecyclerViewAdapter
import project.dheeraj.cinewatch.utils.CONSTANTS

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ViewAllFragment : Fragment() {

    private lateinit var binding: FragmentViewAllBinding
    private val viewModel: ViewAllViewModel by viewModels()

    private lateinit var movieAdapter: ViewAllRecyclerViewAdapter
    private lateinit var movieSkeleton: Skeleton



    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_all , container , false)
        binding = FragmentViewAllBinding.bind(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?): Unit = with(binding) {
        super.onActivityCreated(savedInstanceState)

        movieAdapter = ViewAllRecyclerViewAdapter()
        movieRecyclerView.adapter = movieAdapter
        movieSkeleton = movieRecyclerView.applySkeleton(R.layout.item_bookmark , 15)

        val pageType = requireArguments().get(CONSTANTS.viewAll)
        binding.pageTitle.text = pageType.toString()

        fetchBookmarks()


        buttonBack.setOnClickListener {
            root.findNavController().navigateUp()
        }

    }

    private fun fetchBookmarks() {

        viewModel.bookmarks.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.movieRecyclerView.adapter = BookmarkRecyclerViewAdapter(it)
            }
        }

        viewModel.fetchBookmarks()

    }
}

