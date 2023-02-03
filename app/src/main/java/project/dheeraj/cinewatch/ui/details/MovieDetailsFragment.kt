package project.dheeraj.cinewatch.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import project.dheeraj.cinewatch.R
import project.dheeraj.cinewatch.data.model.Movie
import project.dheeraj.cinewatch.databinding.FragmentMovieDetailsBinding
import project.dheeraj.cinewatch.utils.CONSTANTS
import project.dheeraj.cinewatch.utils.toHours

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), View.OnClickListener {

    private lateinit var movie : Movie

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding : FragmentMovieDetailsBinding


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        binding = FragmentMovieDetailsBinding.bind(view)
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movie = requireArguments().get(CONSTANTS.movie) as Movie

        viewModel.movieName.value = movie.title
        viewModel.movie.value = movie


        binding.buttonBack.setOnClickListener(this)
        binding.buttonBookmark.setOnClickListener(this)



        loadData()



        checkBookmark()

        viewModel.getMovieDetails(movie.id)

    }



    @SuppressLint("SetTextI18n")
    private fun loadData() {

        viewModel.movie.observe(requireActivity(), Observer {

            var genre: String = ""
            if (!it.genres.isNullOrEmpty())
                for (i in 0..it.genres.size - 1) {
                    genre += CONSTANTS.getGenreMap()[it.genres[i].id].toString()
                    if (i != it.genres.size - 1) {
                        genre += "• "
                    }
                }

            binding.apply {
                textMovieName.text = it!!.title
                textRating.text = "${it.vote_average}/10"
                textReleaseDate.text = it.release_date
                textDescription.text = it.overview
                if (it.runtime != null)
                    textLength.text = toHours(it.runtime!!)
                textGenres.text = genre

                detailsBannerImage.load(CONSTANTS.ImageBaseURL + it.backdrop_path) {
                    placeholder(CONSTANTS.viewPagerPlaceHolder.random())
                    error(CONSTANTS.viewPagerPlaceHolder.random())
                }

                imagePoster.load(CONSTANTS.ImageBaseURL + it.poster_path) {
                    placeholder(CONSTANTS.moviePlaceHolder.random())
                    error(CONSTANTS.moviePlaceHolder.random())
                }
            }

        })

    }





    private fun checkBookmark() {

        viewModel.bookmark.observe(viewLifecycleOwner, Observer {
            binding.apply {
                if (it) {
                    buttonBookmark.setImageResource(R.drawable.ic_bookmark_done)
                }
                else {
                    buttonBookmark.setImageResource(R.drawable.ic_bookmark)
                }
            }
        })

        viewModel.checkBookmarkExist()

    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.button_back -> {
                binding.root.findNavController().navigateUp()
            }
            R.id.button_bookmark -> {
                viewModel.bookmarkMovie()
                viewModel.checkBookmarkExist()
            }
        }

    }

}