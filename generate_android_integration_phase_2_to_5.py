import os

base_pkg_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app/app/src/main/java/com/interview/platform"

modules = {
    # Phase 2
    "mod03_department_management": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/departments", "name": "listDepartments"}], "entity": "Department", "domain": "Department"},
    "mod04_technology_management": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/technologies", "name": "listTechnologies"}], "entity": "Technology", "domain": "Technology"},
    "mod05_experience_level_management": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/experience-levels", "name": "listExperienceLevels"}], "entity": "ExperienceLevel", "domain": "ExperienceLevel"},
    "mod09_interview_configuration": {"endpoints": [{"method": "GET", "path": "/api/v1/interviews/configuration/options", "name": "fetchOptions"}], "entity": "InterviewConfig", "domain": "InterviewConfig"},

    # Phase 3
    "mod10_interview_session": {"endpoints": [{"method": "POST", "path": "/api/v1/interviews/sessions", "name": "initializeSession"}], "entity": "InterviewSession", "domain": "InterviewSession"},
    "mod11_question_delivery": {"endpoints": [{"method": "GET", "path": "/api/v1/interviews/sessions/{sessionId}/questions", "name": "retrieveQuestions"}], "entity": "QuestionDelivery", "domain": "QuestionDelivery"},
    "mod12_voice_recording": {"endpoints": [{"method": "POST", "path": "/api/v1/media/audio/upload-url", "name": "requestUploadUrl"}], "entity": "VoiceRecording", "domain": "VoiceRecording"},
    "mod13_speech_to_text": {"endpoints": [{"method": "POST", "path": "/api/v1/media/audio/transcribe", "name": "submitAudioForStt"}], "entity": "SpeechToText", "domain": "SpeechToText"},

    # Phase 4
    "mod14_ai_evaluation": {"endpoints": [{"method": "GET", "path": "/api/v1/interviews/sessions/{sessionId}/evaluation", "name": "retrieveEvaluation"}], "entity": "AiEvaluation", "domain": "AiEvaluation"},
    "mod15_scoring_engine": {"endpoints": [{"method": "GET", "path": "/api/v1/interviews/sessions/{sessionId}/scores", "name": "retrieveScores"}], "entity": "ScoringEngine", "domain": "ScoringEngine"},
    "mod16_feedback_engine": {"endpoints": [{"method": "GET", "path": "/api/v1/interviews/sessions/{sessionId}/feedback", "name": "retrieveFeedback"}], "entity": "FeedbackEngine", "domain": "FeedbackEngine"},
    "mod19_recommendation_engine": {"endpoints": [{"method": "GET", "path": "/api/v1/candidates/recommendations", "name": "retrieveRecommendations"}], "entity": "Recommendation", "domain": "Recommendation"},

    # Phase 5
    "mod17_reporting": {"endpoints": [{"method": "GET", "path": "/api/v1/candidates/reports", "name": "listPastReports"}], "entity": "Reporting", "domain": "Reporting"},
    "mod18_dashboard": {"endpoints": [{"method": "GET", "path": "/api/v1/candidates/dashboard", "name": "retrieveCandidateDashboard"}], "entity": "Dashboard", "domain": "Dashboard"},
    "mod20_analytics": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/analytics/platform", "name": "getPlatformMetrics"}], "entity": "Analytics", "domain": "Analytics"}
}

def to_pascal_case(snake_str):
    components = snake_str.split('_')
    return ''.join(x.title() for x in components)

def generate_integration_layer():
    for mod_pkg, config in modules.items():
        domain_name = config["domain"]
        entity_name = config["entity"]
        
        # Ensure directories exist
        dirs = [
            f"data/remote/dto/{mod_pkg}",
            f"data/local/entity/{mod_pkg}",
            f"data/local/dao",
            f"data/mapper",
            f"data/remote/api",
            f"data/repository",
            f"domain/model/{mod_pkg}",
            f"domain/repository",
            f"di/{mod_pkg}"
        ]
        
        for d in dirs:
            os.makedirs(os.path.join(base_pkg_dir, d), exist_ok=True)

        # 1. DTO
        dto_kt = f"""package com.interview.platform.data.remote.dto.{mod_pkg}

data class {domain_name}RequestDto(
    val payload: String? = null
)

data class {domain_name}ResponseDto(
    val success: Boolean,
    val data: {domain_name}DataDto? = null
)

data class {domain_name}DataDto(
    val id: String
)
"""
        with open(os.path.join(base_pkg_dir, f"data/remote/dto/{mod_pkg}/{domain_name}Dto.kt"), "w") as f:
            f.write(dto_kt)

        # 2. Entity
        entity_kt = f"""package com.interview.platform.data.local.entity.{mod_pkg}

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "{mod_pkg}_table")
data class {entity_name}Entity(
    @PrimaryKey
    val id: String
)
"""
        with open(os.path.join(base_pkg_dir, f"data/local/entity/{mod_pkg}/{entity_name}Entity.kt"), "w") as f:
            f.write(entity_kt)

        # 3. Domain Model
        domain_kt = f"""package com.interview.platform.domain.model.{mod_pkg}

data class {domain_name}(
    val id: String
)
"""
        with open(os.path.join(base_pkg_dir, f"domain/model/{mod_pkg}/{domain_name}.kt"), "w") as f:
            f.write(domain_kt)

        # 4. Mapper
        mapper_kt = f"""package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.{mod_pkg}.{entity_name}Entity
import com.interview.platform.data.remote.dto.{mod_pkg}.{domain_name}DataDto
import com.interview.platform.domain.model.{mod_pkg}.{domain_name}

fun {domain_name}DataDto.toEntity(): {entity_name}Entity {{
    return {entity_name}Entity(id = this.id)
}}

fun {entity_name}Entity.toDomain(): {domain_name} {{
    return {domain_name}(id = this.id)
}}
"""
        with open(os.path.join(base_pkg_dir, f"data/mapper/{domain_name}Mapper.kt"), "w") as f:
            f.write(mapper_kt)

        # 5. API Service
        api_methods = ""
        for ep in config["endpoints"]:
            method = ep["method"]
            path = ep["path"].replace("{", "${")
            name = ep["name"]
            
            # Very basic parameter parsing
            params = ""
            if "sessionId" in path:
                params = "@retrofit2.http.Path(\"sessionId\") sessionId: String"
            
            req_body = f", @retrofit2.http.Body request: com.interview.platform.data.remote.dto.{mod_pkg}.{domain_name}RequestDto" if method in ["POST", "PUT"] else ""
            if req_body and not params:
                req_body = req_body[2:] # Remove leading comma if no params
            
            api_methods += f"""
    @retrofit2.http.{method}("{path}")
    suspend fun {name}({params}{req_body}): retrofit2.Response<com.interview.platform.data.remote.dto.{mod_pkg}.{domain_name}ResponseDto>
"""
            
        api_kt = f"""package com.interview.platform.data.remote.api

interface {to_pascal_case(mod_pkg)}ApiService {{
{api_methods}
}}
"""
        with open(os.path.join(base_pkg_dir, f"data/remote/api/{to_pascal_case(mod_pkg)}ApiService.kt"), "w") as f:
            f.write(api_kt)

        # 6. DAO
        dao_kt = f"""package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.{mod_pkg}.{entity_name}Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface {entity_name}Dao {{
    @Query("SELECT * FROM {mod_pkg}_table")
    fun getAll(): Flow<List<{entity_name}Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: {entity_name}Entity)

    @Query("DELETE FROM {mod_pkg}_table")
    suspend fun deleteAll()
}}
"""
        with open(os.path.join(base_pkg_dir, f"data/local/dao/{entity_name}Dao.kt"), "w") as f:
            f.write(dao_kt)

        # 7. Repository Interface
        repo_iface_kt = f"""package com.interview.platform.domain.repository

import com.interview.platform.domain.model.{mod_pkg}.{domain_name}
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface {domain_name}Repository {{
    fun fetchAll(): Flow<Result<List<{domain_name}>>>
    suspend fun syncData(): Result<Unit>
}}
"""
        with open(os.path.join(base_pkg_dir, f"domain/repository/{domain_name}Repository.kt"), "w") as f:
            f.write(repo_iface_kt)

        # 8. Repository Implementation
        repo_impl_kt = f"""package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.{entity_name}Dao
import com.interview.platform.data.remote.api.{to_pascal_case(mod_pkg)}ApiService
import com.interview.platform.domain.repository.{domain_name}Repository
import com.interview.platform.domain.model.{mod_pkg}.{domain_name}
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class {domain_name}RepositoryImpl @Inject constructor(
    private val apiService: {to_pascal_case(mod_pkg)}ApiService,
    private val dao: {entity_name}Dao
) : {domain_name}Repository {{

    override fun fetchAll(): Flow<Result<List<{domain_name}>>> {{
        return dao.getAll()
            .map {{ list -> list.map {{ it.toDomain() }} }}
            .asResult()
    }}

    override suspend fun syncData(): Result<Unit> {{
        return try {{
            Timber.d("Syncing {domain_name} data")
            Result.Success(Unit)
        }} catch (e: Exception) {{
            Timber.e(e, "Error syncing {domain_name}")
            Result.Error(e)
        }}
    }}
}}
"""
        with open(os.path.join(base_pkg_dir, f"data/repository/{domain_name}RepositoryImpl.kt"), "w") as f:
            f.write(repo_impl_kt)

        # 9. DI Module
        di_kt = f"""package com.interview.platform.di.{mod_pkg}

import com.interview.platform.data.remote.api.{to_pascal_case(mod_pkg)}ApiService
import com.interview.platform.data.repository.{domain_name}RepositoryImpl
import com.interview.platform.domain.repository.{domain_name}Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class {to_pascal_case(mod_pkg)}RepositoryModule {{
    @Binds
    @Singleton
    abstract fun bind{domain_name}Repository(
        impl: {domain_name}RepositoryImpl
    ): {domain_name}Repository
}}

@Module
@InstallIn(SingletonComponent::class)
object {to_pascal_case(mod_pkg)}ApiModule {{
    @Provides
    @Singleton
    fun provide{to_pascal_case(mod_pkg)}ApiService(retrofit: Retrofit): {to_pascal_case(mod_pkg)}ApiService {{
        return retrofit.create({to_pascal_case(mod_pkg)}ApiService::class.java)
    }}
}}
"""
        with open(os.path.join(base_pkg_dir, f"di/{mod_pkg}/{to_pascal_case(mod_pkg)}Module.kt"), "w") as f:
            f.write(di_kt)

generate_integration_layer()
print("Generated Android Integration Layer Phases 2 to 5.")
