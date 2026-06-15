package com.interview.platform;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.interview.platform.data.remote.AuthInterceptor;
import com.interview.platform.data.remote.CorrelationIdInterceptor;
import com.interview.platform.data.remote.TokenProvider;
import com.interview.platform.data.remote.api.Mod01AuthenticationApiService;
import com.interview.platform.data.remote.api.Mod02UserProfileApiService;
import com.interview.platform.data.remote.api.Mod04TechnologyManagementApiService;
import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService;
import com.interview.platform.data.remote.api.Mod11QuestionDeliveryApiService;
import com.interview.platform.data.remote.api.Mod14AiEvaluationApiService;
import com.interview.platform.data.remote.api.Mod19RecommendationEngineApiService;
import com.interview.platform.data.repository.InterviewSessionManager;
import com.interview.platform.data.repository.RecommendationRepositoryImpl;
import com.interview.platform.data.repository.UserPreferencesRepository;
import com.interview.platform.di.AudioModule_ProvideAudioPlayerFactory;
import com.interview.platform.di.AudioModule_ProvideAudioRecorderFactory;
import com.interview.platform.di.AudioModule_ProvideSpeechRecognizerServiceFactory;
import com.interview.platform.di.DataModule_ProvidePreferencesDataStoreFactory;
import com.interview.platform.di.DataModule_ProvideUserPreferencesRepositoryFactory;
import com.interview.platform.di.NetworkModule_ProvideOkHttpClientFactory;
import com.interview.platform.di.NetworkModule_ProvideRetrofitFactory;
import com.interview.platform.di.mod01_authentication.Mod01AuthenticationApiModule_ProvideMod01AuthenticationApiServiceFactory;
import com.interview.platform.di.mod02_user_profile.Mod02UserProfileApiModule_ProvideMod02UserProfileApiServiceFactory;
import com.interview.platform.di.mod04_technology_management.Mod04TechnologyManagementApiModule_ProvideMod04TechnologyManagementApiServiceFactory;
import com.interview.platform.di.mod10_interview_session.Mod10InterviewSessionApiModule_ProvideMod10InterviewSessionApiServiceFactory;
import com.interview.platform.di.mod11_question_delivery.Mod11QuestionDeliveryApiModule_ProvideMod11QuestionDeliveryApiServiceFactory;
import com.interview.platform.di.mod14_ai_evaluation.Mod14AiEvaluationApiModule_ProvideMod14AiEvaluationApiServiceFactory;
import com.interview.platform.di.mod19_recommendation_engine.Mod19RecommendationEngineApiModule_ProvideMod19RecommendationEngineApiServiceFactory;
import com.interview.platform.domain.audio.AudioPlayer;
import com.interview.platform.domain.audio.AudioRecorder;
import com.interview.platform.domain.audio.SpeechRecognizerService;
import com.interview.platform.domain.repository.RecommendationRepository;
import com.interview.platform.ui.screens.mod01_authentication.login.LoginScreenViewModel;
import com.interview.platform.ui.screens.mod01_authentication.login.LoginScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod01_authentication.otp_verification.OtpVerificationScreenViewModel;
import com.interview.platform.ui.screens.mod01_authentication.otp_verification.OtpVerificationScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod01_authentication.permissions.PermissionsScreenViewModel;
import com.interview.platform.ui.screens.mod01_authentication.permissions.PermissionsScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod01_authentication.splash.SplashScreenViewModel;
import com.interview.platform.ui.screens.mod01_authentication.splash.SplashScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod01_authentication.welcome.WelcomeScreenViewModel;
import com.interview.platform.ui.screens.mod01_authentication.welcome.WelcomeScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod02_user_profile.edit_profile.EditProfileScreenViewModel;
import com.interview.platform.ui.screens.mod02_user_profile.edit_profile.EditProfileScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod02_user_profile.profile.ProfileScreenViewModel;
import com.interview.platform.ui.screens.mod02_user_profile.profile.ProfileScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod03_department_management.department_selection.DepartmentSelectionScreenViewModel;
import com.interview.platform.ui.screens.mod03_department_management.department_selection.DepartmentSelectionScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod04_technology_management.technology_selection.TechnologySelectionScreenViewModel;
import com.interview.platform.ui.screens.mod04_technology_management.technology_selection.TechnologySelectionScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod05_experience_level_management.experience_selection.ExperienceSelectionScreenViewModel;
import com.interview.platform.ui.screens.mod05_experience_level_management.experience_selection.ExperienceSelectionScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod09_interview_configuration.home.HomeDashboardScreenViewModel;
import com.interview.platform.ui.screens.mod09_interview_configuration.home.HomeDashboardScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod09_interview_configuration.interview_summary.InterviewSummaryScreenViewModel;
import com.interview.platform.ui.screens.mod09_interview_configuration.interview_summary.InterviewSummaryScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod09_interview_configuration.question_count_selection.QuestionCountSelectionScreenViewModel;
import com.interview.platform.ui.screens.mod09_interview_configuration.question_count_selection.QuestionCountSelectionScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod10_interview_session.audio_review.AudioReviewScreenViewModel;
import com.interview.platform.ui.screens.mod10_interview_session.audio_review.AudioReviewScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod10_interview_session.completed.InterviewCompletedScreenViewModel;
import com.interview.platform.ui.screens.mod10_interview_session.completed.InterviewCompletedScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod10_interview_session.session.InterviewSessionScreenViewModel;
import com.interview.platform.ui.screens.mod10_interview_session.session.InterviewSessionScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod11_question_delivery.question_submitted.QuestionSubmittedScreenViewModel;
import com.interview.platform.ui.screens.mod11_question_delivery.question_submitted.QuestionSubmittedScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod12_voice_recording.recording.VoiceRecordingScreenViewModel;
import com.interview.platform.ui.screens.mod12_voice_recording.recording.VoiceRecordingScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod13_speech_to_text.speech_to_text.SpeechToTextScreenViewModel;
import com.interview.platform.ui.screens.mod13_speech_to_text.speech_to_text.SpeechToTextScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod14_ai_evaluation.detailed_evaluation.DetailedEvaluationScreenViewModel;
import com.interview.platform.ui.screens.mod14_ai_evaluation.detailed_evaluation.DetailedEvaluationScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod14_ai_evaluation.question_wise_analysis.QuestionWiseAnalysisScreenViewModel;
import com.interview.platform.ui.screens.mod14_ai_evaluation.question_wise_analysis.QuestionWiseAnalysisScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod14_ai_evaluation.results_dashboard.ResultsDashboardScreenViewModel;
import com.interview.platform.ui.screens.mod14_ai_evaluation.results_dashboard.ResultsDashboardScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod14_ai_evaluation.strength_analysis.StrengthAnalysisScreenViewModel;
import com.interview.platform.ui.screens.mod14_ai_evaluation.strength_analysis.StrengthAnalysisScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod14_ai_evaluation.weakness_analysis.WeaknessAnalysisScreenViewModel;
import com.interview.platform.ui.screens.mod14_ai_evaluation.weakness_analysis.WeaknessAnalysisScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod16_feedback_engine.ai_feedback.AiFeedbackReportScreenViewModel;
import com.interview.platform.ui.screens.mod16_feedback_engine.ai_feedback.AiFeedbackReportScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod17_reporting.interview_history.InterviewHistoryScreenViewModel;
import com.interview.platform.ui.screens.mod17_reporting.interview_history.InterviewHistoryScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod18_dashboard.HomeDashboardViewModel;
import com.interview.platform.ui.screens.mod18_dashboard.HomeDashboardViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod18_dashboard.achievements.AchievementsScreenViewModel;
import com.interview.platform.ui.screens.mod18_dashboard.achievements.AchievementsScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod19_recommendation_engine.interview_roadmap.InterviewRoadmapScreenViewModel;
import com.interview.platform.ui.screens.mod19_recommendation_engine.interview_roadmap.InterviewRoadmapScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod19_recommendation_engine.learning_recommendations.LearningRecommendationsScreenViewModel;
import com.interview.platform.ui.screens.mod19_recommendation_engine.learning_recommendations.LearningRecommendationsScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod19_recommendation_engine.learning_topic.LearningTopicScreenViewModel;
import com.interview.platform.ui.screens.mod19_recommendation_engine.learning_topic.LearningTopicScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod19_recommendation_engine.practice_plan.PracticePlanScreenViewModel;
import com.interview.platform.ui.screens.mod19_recommendation_engine.practice_plan.PracticePlanScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod19_recommendation_engine.recommended_technologies.RecommendedTechnologiesScreenViewModel;
import com.interview.platform.ui.screens.mod19_recommendation_engine.recommended_technologies.RecommendedTechnologiesScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import com.interview.platform.ui.screens.mod20_analytics.performance_analytics.PerformanceAnalyticsScreenViewModel;
import com.interview.platform.ui.screens.mod20_analytics.performance_analytics.PerformanceAnalyticsScreenViewModel_HiltModules_KeyModule_ProvideFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DaggerMyApp_HiltComponents_SingletonC {
  private DaggerMyApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public MyApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements MyApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public MyApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements MyApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public MyApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements MyApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public MyApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements MyApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MyApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements MyApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MyApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements MyApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public MyApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements MyApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public MyApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends MyApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends MyApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends MyApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends MyApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(34).add(AchievementsScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AiFeedbackReportScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AudioReviewScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(DepartmentSelectionScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(DetailedEvaluationScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(EditProfileScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ExperienceSelectionScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(HomeDashboardScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(HomeDashboardViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(InterviewCompletedScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(InterviewHistoryScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(InterviewRoadmapScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(InterviewSessionScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(InterviewSummaryScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(LearningRecommendationsScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(LearningTopicScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(LoginScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(OtpVerificationScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(PerformanceAnalyticsScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(PermissionsScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(PracticePlanScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ProfileScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(QuestionCountSelectionScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(QuestionSubmittedScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(QuestionWiseAnalysisScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(RecommendedTechnologiesScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ResultsDashboardScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SpeechToTextScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SplashScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(StrengthAnalysisScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(TechnologySelectionScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VoiceRecordingScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(WeaknessAnalysisScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(WelcomeScreenViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends MyApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AchievementsScreenViewModel> achievementsScreenViewModelProvider;

    private Provider<AiFeedbackReportScreenViewModel> aiFeedbackReportScreenViewModelProvider;

    private Provider<AudioReviewScreenViewModel> audioReviewScreenViewModelProvider;

    private Provider<DepartmentSelectionScreenViewModel> departmentSelectionScreenViewModelProvider;

    private Provider<DetailedEvaluationScreenViewModel> detailedEvaluationScreenViewModelProvider;

    private Provider<EditProfileScreenViewModel> editProfileScreenViewModelProvider;

    private Provider<ExperienceSelectionScreenViewModel> experienceSelectionScreenViewModelProvider;

    private Provider<HomeDashboardScreenViewModel> homeDashboardScreenViewModelProvider;

    private Provider<HomeDashboardViewModel> homeDashboardViewModelProvider;

    private Provider<InterviewCompletedScreenViewModel> interviewCompletedScreenViewModelProvider;

    private Provider<InterviewHistoryScreenViewModel> interviewHistoryScreenViewModelProvider;

    private Provider<InterviewRoadmapScreenViewModel> interviewRoadmapScreenViewModelProvider;

    private Provider<InterviewSessionScreenViewModel> interviewSessionScreenViewModelProvider;

    private Provider<InterviewSummaryScreenViewModel> interviewSummaryScreenViewModelProvider;

    private Provider<LearningRecommendationsScreenViewModel> learningRecommendationsScreenViewModelProvider;

    private Provider<LearningTopicScreenViewModel> learningTopicScreenViewModelProvider;

    private Provider<LoginScreenViewModel> loginScreenViewModelProvider;

    private Provider<OtpVerificationScreenViewModel> otpVerificationScreenViewModelProvider;

    private Provider<PerformanceAnalyticsScreenViewModel> performanceAnalyticsScreenViewModelProvider;

    private Provider<PermissionsScreenViewModel> permissionsScreenViewModelProvider;

    private Provider<PracticePlanScreenViewModel> practicePlanScreenViewModelProvider;

    private Provider<ProfileScreenViewModel> profileScreenViewModelProvider;

    private Provider<QuestionCountSelectionScreenViewModel> questionCountSelectionScreenViewModelProvider;

    private Provider<QuestionSubmittedScreenViewModel> questionSubmittedScreenViewModelProvider;

    private Provider<QuestionWiseAnalysisScreenViewModel> questionWiseAnalysisScreenViewModelProvider;

    private Provider<RecommendedTechnologiesScreenViewModel> recommendedTechnologiesScreenViewModelProvider;

    private Provider<ResultsDashboardScreenViewModel> resultsDashboardScreenViewModelProvider;

    private Provider<SpeechToTextScreenViewModel> speechToTextScreenViewModelProvider;

    private Provider<SplashScreenViewModel> splashScreenViewModelProvider;

    private Provider<StrengthAnalysisScreenViewModel> strengthAnalysisScreenViewModelProvider;

    private Provider<TechnologySelectionScreenViewModel> technologySelectionScreenViewModelProvider;

    private Provider<VoiceRecordingScreenViewModel> voiceRecordingScreenViewModelProvider;

    private Provider<WeaknessAnalysisScreenViewModel> weaknessAnalysisScreenViewModelProvider;

    private Provider<WelcomeScreenViewModel> welcomeScreenViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.achievementsScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.aiFeedbackReportScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.audioReviewScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.departmentSelectionScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.detailedEvaluationScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.editProfileScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.experienceSelectionScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.homeDashboardScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.homeDashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.interviewCompletedScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.interviewHistoryScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.interviewRoadmapScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
      this.interviewSessionScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 12);
      this.interviewSummaryScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 13);
      this.learningRecommendationsScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 14);
      this.learningTopicScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 15);
      this.loginScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 16);
      this.otpVerificationScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 17);
      this.performanceAnalyticsScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 18);
      this.permissionsScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 19);
      this.practicePlanScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 20);
      this.profileScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 21);
      this.questionCountSelectionScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 22);
      this.questionSubmittedScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 23);
      this.questionWiseAnalysisScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 24);
      this.recommendedTechnologiesScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 25);
      this.resultsDashboardScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 26);
      this.speechToTextScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 27);
      this.splashScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 28);
      this.strengthAnalysisScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 29);
      this.technologySelectionScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 30);
      this.voiceRecordingScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 31);
      this.weaknessAnalysisScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 32);
      this.welcomeScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 33);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(34).put("com.interview.platform.ui.screens.mod18_dashboard.achievements.AchievementsScreenViewModel", ((Provider) achievementsScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod16_feedback_engine.ai_feedback.AiFeedbackReportScreenViewModel", ((Provider) aiFeedbackReportScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod10_interview_session.audio_review.AudioReviewScreenViewModel", ((Provider) audioReviewScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod03_department_management.department_selection.DepartmentSelectionScreenViewModel", ((Provider) departmentSelectionScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod14_ai_evaluation.detailed_evaluation.DetailedEvaluationScreenViewModel", ((Provider) detailedEvaluationScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod02_user_profile.edit_profile.EditProfileScreenViewModel", ((Provider) editProfileScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod05_experience_level_management.experience_selection.ExperienceSelectionScreenViewModel", ((Provider) experienceSelectionScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod09_interview_configuration.home.HomeDashboardScreenViewModel", ((Provider) homeDashboardScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod18_dashboard.HomeDashboardViewModel", ((Provider) homeDashboardViewModelProvider)).put("com.interview.platform.ui.screens.mod10_interview_session.completed.InterviewCompletedScreenViewModel", ((Provider) interviewCompletedScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod17_reporting.interview_history.InterviewHistoryScreenViewModel", ((Provider) interviewHistoryScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod19_recommendation_engine.interview_roadmap.InterviewRoadmapScreenViewModel", ((Provider) interviewRoadmapScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod10_interview_session.session.InterviewSessionScreenViewModel", ((Provider) interviewSessionScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod09_interview_configuration.interview_summary.InterviewSummaryScreenViewModel", ((Provider) interviewSummaryScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod19_recommendation_engine.learning_recommendations.LearningRecommendationsScreenViewModel", ((Provider) learningRecommendationsScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod19_recommendation_engine.learning_topic.LearningTopicScreenViewModel", ((Provider) learningTopicScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod01_authentication.login.LoginScreenViewModel", ((Provider) loginScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod01_authentication.otp_verification.OtpVerificationScreenViewModel", ((Provider) otpVerificationScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod20_analytics.performance_analytics.PerformanceAnalyticsScreenViewModel", ((Provider) performanceAnalyticsScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod01_authentication.permissions.PermissionsScreenViewModel", ((Provider) permissionsScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod19_recommendation_engine.practice_plan.PracticePlanScreenViewModel", ((Provider) practicePlanScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod02_user_profile.profile.ProfileScreenViewModel", ((Provider) profileScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod09_interview_configuration.question_count_selection.QuestionCountSelectionScreenViewModel", ((Provider) questionCountSelectionScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod11_question_delivery.question_submitted.QuestionSubmittedScreenViewModel", ((Provider) questionSubmittedScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod14_ai_evaluation.question_wise_analysis.QuestionWiseAnalysisScreenViewModel", ((Provider) questionWiseAnalysisScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod19_recommendation_engine.recommended_technologies.RecommendedTechnologiesScreenViewModel", ((Provider) recommendedTechnologiesScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod14_ai_evaluation.results_dashboard.ResultsDashboardScreenViewModel", ((Provider) resultsDashboardScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod13_speech_to_text.speech_to_text.SpeechToTextScreenViewModel", ((Provider) speechToTextScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod01_authentication.splash.SplashScreenViewModel", ((Provider) splashScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod14_ai_evaluation.strength_analysis.StrengthAnalysisScreenViewModel", ((Provider) strengthAnalysisScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod04_technology_management.technology_selection.TechnologySelectionScreenViewModel", ((Provider) technologySelectionScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod12_voice_recording.recording.VoiceRecordingScreenViewModel", ((Provider) voiceRecordingScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod14_ai_evaluation.weakness_analysis.WeaknessAnalysisScreenViewModel", ((Provider) weaknessAnalysisScreenViewModelProvider)).put("com.interview.platform.ui.screens.mod01_authentication.welcome.WelcomeScreenViewModel", ((Provider) welcomeScreenViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return Collections.<String, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.interview.platform.ui.screens.mod18_dashboard.achievements.AchievementsScreenViewModel 
          return (T) new AchievementsScreenViewModel();

          case 1: // com.interview.platform.ui.screens.mod16_feedback_engine.ai_feedback.AiFeedbackReportScreenViewModel 
          return (T) new AiFeedbackReportScreenViewModel(singletonCImpl.interviewSessionManagerProvider.get());

          case 2: // com.interview.platform.ui.screens.mod10_interview_session.audio_review.AudioReviewScreenViewModel 
          return (T) new AudioReviewScreenViewModel(singletonCImpl.provideAudioPlayerProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.interview.platform.ui.screens.mod03_department_management.department_selection.DepartmentSelectionScreenViewModel 
          return (T) new DepartmentSelectionScreenViewModel();

          case 4: // com.interview.platform.ui.screens.mod14_ai_evaluation.detailed_evaluation.DetailedEvaluationScreenViewModel 
          return (T) new DetailedEvaluationScreenViewModel(singletonCImpl.interviewSessionManagerProvider.get());

          case 5: // com.interview.platform.ui.screens.mod02_user_profile.edit_profile.EditProfileScreenViewModel 
          return (T) new EditProfileScreenViewModel();

          case 6: // com.interview.platform.ui.screens.mod05_experience_level_management.experience_selection.ExperienceSelectionScreenViewModel 
          return (T) new ExperienceSelectionScreenViewModel(singletonCImpl.provideUserPreferencesRepositoryProvider.get(), singletonCImpl.interviewSessionManagerProvider.get());

          case 7: // com.interview.platform.ui.screens.mod09_interview_configuration.home.HomeDashboardScreenViewModel 
          return (T) new HomeDashboardScreenViewModel();

          case 8: // com.interview.platform.ui.screens.mod18_dashboard.HomeDashboardViewModel 
          return (T) new HomeDashboardViewModel(singletonCImpl.provideUserPreferencesRepositoryProvider.get(), singletonCImpl.provideMod02UserProfileApiServiceProvider.get(), singletonCImpl.provideMod10InterviewSessionApiServiceProvider.get(), singletonCImpl.interviewSessionManagerProvider.get());

          case 9: // com.interview.platform.ui.screens.mod10_interview_session.completed.InterviewCompletedScreenViewModel 
          return (T) new InterviewCompletedScreenViewModel();

          case 10: // com.interview.platform.ui.screens.mod17_reporting.interview_history.InterviewHistoryScreenViewModel 
          return (T) new InterviewHistoryScreenViewModel(singletonCImpl.provideMod10InterviewSessionApiServiceProvider.get());

          case 11: // com.interview.platform.ui.screens.mod19_recommendation_engine.interview_roadmap.InterviewRoadmapScreenViewModel 
          return (T) new InterviewRoadmapScreenViewModel(singletonCImpl.provideMod19RecommendationEngineApiServiceProvider.get(), singletonCImpl.provideUserPreferencesRepositoryProvider.get(), singletonCImpl.bindRecommendationRepositoryProvider.get());

          case 12: // com.interview.platform.ui.screens.mod10_interview_session.session.InterviewSessionScreenViewModel 
          return (T) new InterviewSessionScreenViewModel(singletonCImpl.provideMod11QuestionDeliveryApiServiceProvider.get(), singletonCImpl.provideMod10InterviewSessionApiServiceProvider.get(), singletonCImpl.interviewSessionManagerProvider.get());

          case 13: // com.interview.platform.ui.screens.mod09_interview_configuration.interview_summary.InterviewSummaryScreenViewModel 
          return (T) new InterviewSummaryScreenViewModel(singletonCImpl.provideMod10InterviewSessionApiServiceProvider.get(), singletonCImpl.interviewSessionManagerProvider.get());

          case 14: // com.interview.platform.ui.screens.mod19_recommendation_engine.learning_recommendations.LearningRecommendationsScreenViewModel 
          return (T) new LearningRecommendationsScreenViewModel();

          case 15: // com.interview.platform.ui.screens.mod19_recommendation_engine.learning_topic.LearningTopicScreenViewModel 
          return (T) new LearningTopicScreenViewModel(singletonCImpl.bindRecommendationRepositoryProvider.get());

          case 16: // com.interview.platform.ui.screens.mod01_authentication.login.LoginScreenViewModel 
          return (T) new LoginScreenViewModel(singletonCImpl.provideMod01AuthenticationApiServiceProvider.get());

          case 17: // com.interview.platform.ui.screens.mod01_authentication.otp_verification.OtpVerificationScreenViewModel 
          return (T) new OtpVerificationScreenViewModel(singletonCImpl.provideMod01AuthenticationApiServiceProvider.get(), singletonCImpl.provideMod02UserProfileApiServiceProvider.get(), singletonCImpl.provideUserPreferencesRepositoryProvider.get(), singletonCImpl.tokenProvider.get());

          case 18: // com.interview.platform.ui.screens.mod20_analytics.performance_analytics.PerformanceAnalyticsScreenViewModel 
          return (T) new PerformanceAnalyticsScreenViewModel();

          case 19: // com.interview.platform.ui.screens.mod01_authentication.permissions.PermissionsScreenViewModel 
          return (T) new PermissionsScreenViewModel();

          case 20: // com.interview.platform.ui.screens.mod19_recommendation_engine.practice_plan.PracticePlanScreenViewModel 
          return (T) new PracticePlanScreenViewModel();

          case 21: // com.interview.platform.ui.screens.mod02_user_profile.profile.ProfileScreenViewModel 
          return (T) new ProfileScreenViewModel(singletonCImpl.provideUserPreferencesRepositoryProvider.get());

          case 22: // com.interview.platform.ui.screens.mod09_interview_configuration.question_count_selection.QuestionCountSelectionScreenViewModel 
          return (T) new QuestionCountSelectionScreenViewModel(singletonCImpl.interviewSessionManagerProvider.get());

          case 23: // com.interview.platform.ui.screens.mod11_question_delivery.question_submitted.QuestionSubmittedScreenViewModel 
          return (T) new QuestionSubmittedScreenViewModel();

          case 24: // com.interview.platform.ui.screens.mod14_ai_evaluation.question_wise_analysis.QuestionWiseAnalysisScreenViewModel 
          return (T) new QuestionWiseAnalysisScreenViewModel(singletonCImpl.interviewSessionManagerProvider.get());

          case 25: // com.interview.platform.ui.screens.mod19_recommendation_engine.recommended_technologies.RecommendedTechnologiesScreenViewModel 
          return (T) new RecommendedTechnologiesScreenViewModel(singletonCImpl.bindRecommendationRepositoryProvider.get());

          case 26: // com.interview.platform.ui.screens.mod14_ai_evaluation.results_dashboard.ResultsDashboardScreenViewModel 
          return (T) new ResultsDashboardScreenViewModel(singletonCImpl.provideMod14AiEvaluationApiServiceProvider.get(), singletonCImpl.interviewSessionManagerProvider.get());

          case 27: // com.interview.platform.ui.screens.mod13_speech_to_text.speech_to_text.SpeechToTextScreenViewModel 
          return (T) new SpeechToTextScreenViewModel(singletonCImpl.provideSpeechRecognizerServiceProvider.get(), singletonCImpl.provideMod10InterviewSessionApiServiceProvider.get(), singletonCImpl.interviewSessionManagerProvider.get());

          case 28: // com.interview.platform.ui.screens.mod01_authentication.splash.SplashScreenViewModel 
          return (T) new SplashScreenViewModel(singletonCImpl.provideUserPreferencesRepositoryProvider.get(), singletonCImpl.tokenProvider.get());

          case 29: // com.interview.platform.ui.screens.mod14_ai_evaluation.strength_analysis.StrengthAnalysisScreenViewModel 
          return (T) new StrengthAnalysisScreenViewModel(singletonCImpl.interviewSessionManagerProvider.get());

          case 30: // com.interview.platform.ui.screens.mod04_technology_management.technology_selection.TechnologySelectionScreenViewModel 
          return (T) new TechnologySelectionScreenViewModel(singletonCImpl.provideUserPreferencesRepositoryProvider.get(), singletonCImpl.interviewSessionManagerProvider.get(), singletonCImpl.provideMod02UserProfileApiServiceProvider.get(), singletonCImpl.provideMod04TechnologyManagementApiServiceProvider.get());

          case 31: // com.interview.platform.ui.screens.mod12_voice_recording.recording.VoiceRecordingScreenViewModel 
          return (T) new VoiceRecordingScreenViewModel(singletonCImpl.provideAudioRecorderProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 32: // com.interview.platform.ui.screens.mod14_ai_evaluation.weakness_analysis.WeaknessAnalysisScreenViewModel 
          return (T) new WeaknessAnalysisScreenViewModel(singletonCImpl.interviewSessionManagerProvider.get());

          case 33: // com.interview.platform.ui.screens.mod01_authentication.welcome.WelcomeScreenViewModel 
          return (T) new WelcomeScreenViewModel();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends MyApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends MyApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends MyApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<InterviewSessionManager> interviewSessionManagerProvider;

    private Provider<AudioPlayer> provideAudioPlayerProvider;

    private Provider<DataStore<Preferences>> providePreferencesDataStoreProvider;

    private Provider<UserPreferencesRepository> provideUserPreferencesRepositoryProvider;

    private Provider<TokenProvider> tokenProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<Mod02UserProfileApiService> provideMod02UserProfileApiServiceProvider;

    private Provider<Mod10InterviewSessionApiService> provideMod10InterviewSessionApiServiceProvider;

    private Provider<Mod19RecommendationEngineApiService> provideMod19RecommendationEngineApiServiceProvider;

    private Provider<RecommendationRepositoryImpl> recommendationRepositoryImplProvider;

    private Provider<RecommendationRepository> bindRecommendationRepositoryProvider;

    private Provider<Mod11QuestionDeliveryApiService> provideMod11QuestionDeliveryApiServiceProvider;

    private Provider<Mod01AuthenticationApiService> provideMod01AuthenticationApiServiceProvider;

    private Provider<Mod14AiEvaluationApiService> provideMod14AiEvaluationApiServiceProvider;

    private Provider<SpeechRecognizerService> provideSpeechRecognizerServiceProvider;

    private Provider<Mod04TechnologyManagementApiService> provideMod04TechnologyManagementApiServiceProvider;

    private Provider<AudioRecorder> provideAudioRecorderProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private AuthInterceptor authInterceptor() {
      return new AuthInterceptor(tokenProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.interviewSessionManagerProvider = DoubleCheck.provider(new SwitchingProvider<InterviewSessionManager>(singletonCImpl, 0));
      this.provideAudioPlayerProvider = DoubleCheck.provider(new SwitchingProvider<AudioPlayer>(singletonCImpl, 1));
      this.providePreferencesDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<DataStore<Preferences>>(singletonCImpl, 3));
      this.provideUserPreferencesRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<UserPreferencesRepository>(singletonCImpl, 2));
      this.tokenProvider = DoubleCheck.provider(new SwitchingProvider<TokenProvider>(singletonCImpl, 7));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 6));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 5));
      this.provideMod02UserProfileApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<Mod02UserProfileApiService>(singletonCImpl, 4));
      this.provideMod10InterviewSessionApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<Mod10InterviewSessionApiService>(singletonCImpl, 8));
      this.provideMod19RecommendationEngineApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<Mod19RecommendationEngineApiService>(singletonCImpl, 9));
      this.recommendationRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 10);
      this.bindRecommendationRepositoryProvider = DoubleCheck.provider((Provider) recommendationRepositoryImplProvider);
      this.provideMod11QuestionDeliveryApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<Mod11QuestionDeliveryApiService>(singletonCImpl, 11));
      this.provideMod01AuthenticationApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<Mod01AuthenticationApiService>(singletonCImpl, 12));
      this.provideMod14AiEvaluationApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<Mod14AiEvaluationApiService>(singletonCImpl, 13));
      this.provideSpeechRecognizerServiceProvider = DoubleCheck.provider(new SwitchingProvider<SpeechRecognizerService>(singletonCImpl, 14));
      this.provideMod04TechnologyManagementApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<Mod04TechnologyManagementApiService>(singletonCImpl, 15));
      this.provideAudioRecorderProvider = DoubleCheck.provider(new SwitchingProvider<AudioRecorder>(singletonCImpl, 16));
    }

    @Override
    public void injectMyApp(MyApp myApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.interview.platform.data.repository.InterviewSessionManager 
          return (T) new InterviewSessionManager();

          case 1: // com.interview.platform.domain.audio.AudioPlayer 
          return (T) AudioModule_ProvideAudioPlayerFactory.provideAudioPlayer(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.interview.platform.data.repository.UserPreferencesRepository 
          return (T) DataModule_ProvideUserPreferencesRepositoryFactory.provideUserPreferencesRepository(singletonCImpl.providePreferencesDataStoreProvider.get());

          case 3: // androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> 
          return (T) DataModule_ProvidePreferencesDataStoreFactory.providePreferencesDataStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.interview.platform.data.remote.api.Mod02UserProfileApiService 
          return (T) Mod02UserProfileApiModule_ProvideMod02UserProfileApiServiceFactory.provideMod02UserProfileApiService(singletonCImpl.provideRetrofitProvider.get());

          case 5: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 6: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(singletonCImpl.authInterceptor(), new CorrelationIdInterceptor());

          case 7: // com.interview.platform.data.remote.TokenProvider 
          return (T) new TokenProvider();

          case 8: // com.interview.platform.data.remote.api.Mod10InterviewSessionApiService 
          return (T) Mod10InterviewSessionApiModule_ProvideMod10InterviewSessionApiServiceFactory.provideMod10InterviewSessionApiService(singletonCImpl.provideRetrofitProvider.get());

          case 9: // com.interview.platform.data.remote.api.Mod19RecommendationEngineApiService 
          return (T) Mod19RecommendationEngineApiModule_ProvideMod19RecommendationEngineApiServiceFactory.provideMod19RecommendationEngineApiService(singletonCImpl.provideRetrofitProvider.get());

          case 10: // com.interview.platform.data.repository.RecommendationRepositoryImpl 
          return (T) new RecommendationRepositoryImpl(singletonCImpl.provideMod19RecommendationEngineApiServiceProvider.get());

          case 11: // com.interview.platform.data.remote.api.Mod11QuestionDeliveryApiService 
          return (T) Mod11QuestionDeliveryApiModule_ProvideMod11QuestionDeliveryApiServiceFactory.provideMod11QuestionDeliveryApiService(singletonCImpl.provideRetrofitProvider.get());

          case 12: // com.interview.platform.data.remote.api.Mod01AuthenticationApiService 
          return (T) Mod01AuthenticationApiModule_ProvideMod01AuthenticationApiServiceFactory.provideMod01AuthenticationApiService(singletonCImpl.provideRetrofitProvider.get());

          case 13: // com.interview.platform.data.remote.api.Mod14AiEvaluationApiService 
          return (T) Mod14AiEvaluationApiModule_ProvideMod14AiEvaluationApiServiceFactory.provideMod14AiEvaluationApiService(singletonCImpl.provideRetrofitProvider.get());

          case 14: // com.interview.platform.domain.audio.SpeechRecognizerService 
          return (T) AudioModule_ProvideSpeechRecognizerServiceFactory.provideSpeechRecognizerService(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 15: // com.interview.platform.data.remote.api.Mod04TechnologyManagementApiService 
          return (T) Mod04TechnologyManagementApiModule_ProvideMod04TechnologyManagementApiServiceFactory.provideMod04TechnologyManagementApiService(singletonCImpl.provideRetrofitProvider.get());

          case 16: // com.interview.platform.domain.audio.AudioRecorder 
          return (T) AudioModule_ProvideAudioRecorderFactory.provideAudioRecorder(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
