package com.interview.platform;

import com.interview.platform.di.AudioModule;
import com.interview.platform.di.DataModule;
import com.interview.platform.di.NetworkModule;
import com.interview.platform.di.mod01_authentication.Mod01AuthenticationApiModule;
import com.interview.platform.di.mod01_authentication.Mod01AuthenticationRepositoryModule;
import com.interview.platform.di.mod02_user_profile.Mod02UserProfileApiModule;
import com.interview.platform.di.mod02_user_profile.Mod02UserProfileRepositoryModule;
import com.interview.platform.di.mod03_department_management.Mod03DepartmentManagementApiModule;
import com.interview.platform.di.mod03_department_management.Mod03DepartmentManagementRepositoryModule;
import com.interview.platform.di.mod04_technology_management.Mod04TechnologyManagementApiModule;
import com.interview.platform.di.mod04_technology_management.Mod04TechnologyManagementRepositoryModule;
import com.interview.platform.di.mod05_experience_level_management.Mod05ExperienceLevelManagementApiModule;
import com.interview.platform.di.mod05_experience_level_management.Mod05ExperienceLevelManagementRepositoryModule;
import com.interview.platform.di.mod09_interview_configuration.Mod09InterviewConfigurationApiModule;
import com.interview.platform.di.mod09_interview_configuration.Mod09InterviewConfigurationRepositoryModule;
import com.interview.platform.di.mod10_interview_session.Mod10InterviewSessionApiModule;
import com.interview.platform.di.mod10_interview_session.Mod10InterviewSessionRepositoryModule;
import com.interview.platform.di.mod11_question_delivery.Mod11QuestionDeliveryApiModule;
import com.interview.platform.di.mod11_question_delivery.Mod11QuestionDeliveryRepositoryModule;
import com.interview.platform.di.mod12_voice_recording.Mod12VoiceRecordingApiModule;
import com.interview.platform.di.mod12_voice_recording.Mod12VoiceRecordingRepositoryModule;
import com.interview.platform.di.mod13_speech_to_text.Mod13SpeechToTextApiModule;
import com.interview.platform.di.mod13_speech_to_text.Mod13SpeechToTextRepositoryModule;
import com.interview.platform.di.mod14_ai_evaluation.Mod14AiEvaluationApiModule;
import com.interview.platform.di.mod14_ai_evaluation.Mod14AiEvaluationRepositoryModule;
import com.interview.platform.di.mod15_scoring_engine.Mod15ScoringEngineApiModule;
import com.interview.platform.di.mod15_scoring_engine.Mod15ScoringEngineRepositoryModule;
import com.interview.platform.di.mod16_feedback_engine.Mod16FeedbackEngineApiModule;
import com.interview.platform.di.mod16_feedback_engine.Mod16FeedbackEngineRepositoryModule;
import com.interview.platform.di.mod17_reporting.Mod17ReportingApiModule;
import com.interview.platform.di.mod17_reporting.Mod17ReportingRepositoryModule;
import com.interview.platform.di.mod18_dashboard.Mod18DashboardApiModule;
import com.interview.platform.di.mod18_dashboard.Mod18DashboardRepositoryModule;
import com.interview.platform.di.mod19_recommendation_engine.Mod19RecommendationEngineApiModule;
import com.interview.platform.di.mod19_recommendation_engine.Mod19RecommendationEngineRepositoryModule;
import com.interview.platform.di.mod20_analytics.Mod20AnalyticsApiModule;
import com.interview.platform.di.mod20_analytics.Mod20AnalyticsRepositoryModule;
import com.interview.platform.ui.screens.mod01_authentication.login.LoginScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod01_authentication.otp_verification.OtpVerificationScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod01_authentication.permissions.PermissionsScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod01_authentication.splash.SplashScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod01_authentication.welcome.WelcomeScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod02_user_profile.edit_profile.EditProfileScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod02_user_profile.profile.ProfileScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod03_department_management.department_selection.DepartmentSelectionScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod04_technology_management.technology_selection.TechnologySelectionScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod05_experience_level_management.experience_selection.ExperienceSelectionScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod09_interview_configuration.home.HomeDashboardScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod09_interview_configuration.interview_summary.InterviewSummaryScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod09_interview_configuration.question_count_selection.QuestionCountSelectionScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod10_interview_session.audio_review.AudioReviewScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod10_interview_session.completed.InterviewCompletedScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod10_interview_session.session.InterviewSessionScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod11_question_delivery.question_submitted.QuestionSubmittedScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod12_voice_recording.recording.VoiceRecordingScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod13_speech_to_text.speech_to_text.SpeechToTextScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod14_ai_evaluation.detailed_evaluation.DetailedEvaluationScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod14_ai_evaluation.question_wise_analysis.QuestionWiseAnalysisScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod14_ai_evaluation.results_dashboard.ResultsDashboardScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod14_ai_evaluation.strength_analysis.StrengthAnalysisScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod14_ai_evaluation.weakness_analysis.WeaknessAnalysisScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod16_feedback_engine.ai_feedback.AiFeedbackReportScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod17_reporting.interview_history.InterviewHistoryScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod18_dashboard.HomeDashboardViewModel_HiltModules;
import com.interview.platform.ui.screens.mod18_dashboard.achievements.AchievementsScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod19_recommendation_engine.interview_roadmap.InterviewRoadmapScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod19_recommendation_engine.learning_recommendations.LearningRecommendationsScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod19_recommendation_engine.learning_topic.LearningTopicScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod19_recommendation_engine.practice_plan.PracticePlanScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod19_recommendation_engine.recommended_technologies.RecommendedTechnologiesScreenViewModel_HiltModules;
import com.interview.platform.ui.screens.mod20_analytics.performance_analytics.PerformanceAnalyticsScreenViewModel_HiltModules;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.HiltWrapper_SavedStateHandleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.annotation.processing.Generated;
import javax.inject.Singleton;

@Generated("dagger.hilt.processor.internal.root.RootProcessor")
public final class MyApp_HiltComponents {
  private MyApp_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
          ApplicationContextModule.class,
          AudioModule.class,
          DataModule.class,
          HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule.class,
          Mod01AuthenticationApiModule.class,
          Mod01AuthenticationRepositoryModule.class,
          Mod02UserProfileApiModule.class,
          Mod02UserProfileRepositoryModule.class,
          Mod03DepartmentManagementApiModule.class,
          Mod03DepartmentManagementRepositoryModule.class,
          Mod04TechnologyManagementApiModule.class,
          Mod04TechnologyManagementRepositoryModule.class,
          Mod05ExperienceLevelManagementApiModule.class,
          Mod05ExperienceLevelManagementRepositoryModule.class,
          Mod09InterviewConfigurationApiModule.class,
          Mod09InterviewConfigurationRepositoryModule.class,
          Mod10InterviewSessionApiModule.class,
          Mod10InterviewSessionRepositoryModule.class,
          Mod11QuestionDeliveryApiModule.class,
          Mod11QuestionDeliveryRepositoryModule.class,
          Mod12VoiceRecordingApiModule.class,
          Mod12VoiceRecordingRepositoryModule.class,
          Mod13SpeechToTextApiModule.class,
          Mod13SpeechToTextRepositoryModule.class,
          Mod14AiEvaluationApiModule.class,
          Mod14AiEvaluationRepositoryModule.class,
          Mod15ScoringEngineApiModule.class,
          Mod15ScoringEngineRepositoryModule.class,
          Mod16FeedbackEngineApiModule.class,
          Mod16FeedbackEngineRepositoryModule.class,
          Mod17ReportingApiModule.class,
          Mod17ReportingRepositoryModule.class,
          Mod18DashboardApiModule.class,
          Mod18DashboardRepositoryModule.class,
          Mod19RecommendationEngineApiModule.class,
          Mod19RecommendationEngineRepositoryModule.class,
          Mod20AnalyticsApiModule.class,
          Mod20AnalyticsRepositoryModule.class,
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class,
          NetworkModule.class
      }
  )
  @Singleton
  public abstract static class SingletonC implements MyApp_GeneratedInjector,
      FragmentGetContextFix.FragmentGetContextFixEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      GeneratedComponent {
  }

  @Subcomponent
  @ServiceScoped
  public abstract static class ServiceC implements ServiceComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AchievementsScreenViewModel_HiltModules.KeyModule.class,
          AiFeedbackReportScreenViewModel_HiltModules.KeyModule.class,
          AudioReviewScreenViewModel_HiltModules.KeyModule.class,
          DepartmentSelectionScreenViewModel_HiltModules.KeyModule.class,
          DetailedEvaluationScreenViewModel_HiltModules.KeyModule.class,
          EditProfileScreenViewModel_HiltModules.KeyModule.class,
          ExperienceSelectionScreenViewModel_HiltModules.KeyModule.class,
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          HiltWrapper_SavedStateHandleModule.class,
          HomeDashboardScreenViewModel_HiltModules.KeyModule.class,
          HomeDashboardViewModel_HiltModules.KeyModule.class,
          InterviewCompletedScreenViewModel_HiltModules.KeyModule.class,
          InterviewHistoryScreenViewModel_HiltModules.KeyModule.class,
          InterviewRoadmapScreenViewModel_HiltModules.KeyModule.class,
          InterviewSessionScreenViewModel_HiltModules.KeyModule.class,
          InterviewSummaryScreenViewModel_HiltModules.KeyModule.class,
          LearningRecommendationsScreenViewModel_HiltModules.KeyModule.class,
          LearningTopicScreenViewModel_HiltModules.KeyModule.class,
          LoginScreenViewModel_HiltModules.KeyModule.class,
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class,
          OtpVerificationScreenViewModel_HiltModules.KeyModule.class,
          PerformanceAnalyticsScreenViewModel_HiltModules.KeyModule.class,
          PermissionsScreenViewModel_HiltModules.KeyModule.class,
          PracticePlanScreenViewModel_HiltModules.KeyModule.class,
          ProfileScreenViewModel_HiltModules.KeyModule.class,
          QuestionCountSelectionScreenViewModel_HiltModules.KeyModule.class,
          QuestionSubmittedScreenViewModel_HiltModules.KeyModule.class,
          QuestionWiseAnalysisScreenViewModel_HiltModules.KeyModule.class,
          RecommendedTechnologiesScreenViewModel_HiltModules.KeyModule.class,
          ResultsDashboardScreenViewModel_HiltModules.KeyModule.class,
          SpeechToTextScreenViewModel_HiltModules.KeyModule.class,
          SplashScreenViewModel_HiltModules.KeyModule.class,
          StrengthAnalysisScreenViewModel_HiltModules.KeyModule.class,
          TechnologySelectionScreenViewModel_HiltModules.KeyModule.class,
          VoiceRecordingScreenViewModel_HiltModules.KeyModule.class,
          WeaknessAnalysisScreenViewModel_HiltModules.KeyModule.class,
          WelcomeScreenViewModel_HiltModules.KeyModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class,
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements MainActivity_GeneratedInjector,
      ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AchievementsScreenViewModel_HiltModules.BindsModule.class,
          AiFeedbackReportScreenViewModel_HiltModules.BindsModule.class,
          AudioReviewScreenViewModel_HiltModules.BindsModule.class,
          DepartmentSelectionScreenViewModel_HiltModules.BindsModule.class,
          DetailedEvaluationScreenViewModel_HiltModules.BindsModule.class,
          EditProfileScreenViewModel_HiltModules.BindsModule.class,
          ExperienceSelectionScreenViewModel_HiltModules.BindsModule.class,
          HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
          HomeDashboardScreenViewModel_HiltModules.BindsModule.class,
          HomeDashboardViewModel_HiltModules.BindsModule.class,
          InterviewCompletedScreenViewModel_HiltModules.BindsModule.class,
          InterviewHistoryScreenViewModel_HiltModules.BindsModule.class,
          InterviewRoadmapScreenViewModel_HiltModules.BindsModule.class,
          InterviewSessionScreenViewModel_HiltModules.BindsModule.class,
          InterviewSummaryScreenViewModel_HiltModules.BindsModule.class,
          LearningRecommendationsScreenViewModel_HiltModules.BindsModule.class,
          LearningTopicScreenViewModel_HiltModules.BindsModule.class,
          LoginScreenViewModel_HiltModules.BindsModule.class,
          OtpVerificationScreenViewModel_HiltModules.BindsModule.class,
          PerformanceAnalyticsScreenViewModel_HiltModules.BindsModule.class,
          PermissionsScreenViewModel_HiltModules.BindsModule.class,
          PracticePlanScreenViewModel_HiltModules.BindsModule.class,
          ProfileScreenViewModel_HiltModules.BindsModule.class,
          QuestionCountSelectionScreenViewModel_HiltModules.BindsModule.class,
          QuestionSubmittedScreenViewModel_HiltModules.BindsModule.class,
          QuestionWiseAnalysisScreenViewModel_HiltModules.BindsModule.class,
          RecommendedTechnologiesScreenViewModel_HiltModules.BindsModule.class,
          ResultsDashboardScreenViewModel_HiltModules.BindsModule.class,
          SpeechToTextScreenViewModel_HiltModules.BindsModule.class,
          SplashScreenViewModel_HiltModules.BindsModule.class,
          StrengthAnalysisScreenViewModel_HiltModules.BindsModule.class,
          TechnologySelectionScreenViewModel_HiltModules.BindsModule.class,
          VoiceRecordingScreenViewModel_HiltModules.BindsModule.class,
          WeaknessAnalysisScreenViewModel_HiltModules.BindsModule.class,
          WelcomeScreenViewModel_HiltModules.BindsModule.class
      }
  )
  @ViewModelScoped
  public abstract static class ViewModelC implements ViewModelComponent,
      HiltViewModelFactory.ViewModelFactoriesEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewModelComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = ViewWithFragmentCBuilderModule.class
  )
  @FragmentScoped
  public abstract static class FragmentC implements FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
