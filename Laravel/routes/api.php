<?php

use App\Models\DemandeEvent;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\SalleController;
use App\Http\Controllers\API\AuthController;
use App\Http\Controllers\MaterielController;

use App\Http\Controllers\club\ClubController;
use App\Http\Controllers\club\MemberController;
use App\Http\Controllers\DepartementController;
use App\Http\Controllers\DemandeEventController;
use App\Http\Controllers\DemandeSalleController;

use App\Http\Controllers\DemandeMaterielController;
use App\Http\Controllers\CategorieMaterielController;
use App\Http\Controllers\club\pageClub\AboutController;
use App\Http\Controllers\club\pageClub\BoardController;
use App\Http\Controllers\DemandeCreationClubController;
use App\Http\Controllers\club\pageClub\HeadersController;
use App\Http\Controllers\club\pageClub\ProjectsController;
use App\Http\Controllers\club\pageClub\ActivitiesController;

use App\Http\Controllers\ChefDepartmentController;





use App\Http\Controllers\ClasseController;
use App\Http\Controllers\StudentsController;


/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/


Route::group(['prefix' => '/c'], function () {
    Route::get('/', [CategorieMaterielController::class, 'show']);
});

Route::group(['prefix' => '/cd'], function () {
    Route::get('/', [ChefDepartmentController::class, 'show']);
    Route::post('/accept/{id}', [ChefDepartmentController::class, 'accept']);
    Route::post('/refuse/{id}', [ChefDepartmentController::class, 'refuse']);
});




Route::post('login', [AuthController::class, 'signin']);
Route::post('register', [AuthController::class, 'signup']);
// Sanctum middleware
Route::middleware('auth:sanctum')->group(function () {
    // demande creation club routes
    Route::group(['prefix' => '/dcc'], function () {
        Route::get('/', [DemandeCreationClubController::class, 'show']);
        Route::get('/{id}', [DemandeCreationClubController::class, 'showMyDemandes']);
        Route::post('/', [DemandeCreationClubController::class, 'create']);
        Route::put('/{id}', [DemandeCreationClubController::class, 'update']);
        Route::delete('/{id}', [DemandeCreationClubController::class, 'delete']);
        Route::put('/a/{id}', [DemandeCreationClubController::class, 'accept']);
        Route::put('/d/{id}', [DemandeCreationClubController::class, 'decline']);
    });
    // demande adhesion club routes
    Route::group(['prefix' => '/dac'], function () {
        Route::prefix('/responsable')->group(function () {
            Route::get('/', [ClubController::class, 'getDemandeAdhesionByClub']);
            Route::put('/a/{id}', [ClubController::class, 'acceptDemandeAdhesion']);
            Route::put('/d/{id}', [ClubController::class, 'refuserDemandeAdhesion']);
        });
        Route::post('/', [ClubController::class, 'DemandeeAdhesion']);
        Route::get('/a', [ClubController::class, 'getDemandeAdhesion']);
        Route::delete('/{id}', [ClubController::class, 'deleteDemandeAdhesion']);
    });
    // Route::apiResource('secrets', SecretsController::class);
    Route::get('/test', [DemandeCreationClubController::class, 'test']);
    // Route::get('/dcc', [DemandeCreationClubController::class, 'get']);

    // demande salle routes
    Route::group(['prefix' => '/DemandeSalle'], function () {
        Route::get('/', [DemandeSalleController::class, 'index']);
        Route::post('/', [DemandeSalleController::class, 'store']);
        Route::put('/{id}', [DemandeSalleController::class, 'update']);
        Route::delete('/{id}', [DemandeSalleController::class, 'destroy']);
    });
    // demande materiel routes

    Route::group(['prefix' => '/dm'], function () {
        Route::get('/', [DemandeMaterielController::class, 'show']);
        // Route::get('/{id}',[DemandeMaterielController::class,'showMyDemandes']);
        Route::post('/', [DemandeMaterielController::class, 'create']);
        Route::put('/{id}', [DemandeMaterielController::class, 'update']);
        Route::delete('/{id}', [DemandeMaterielController::class, 'delete']);
    });

    // about routes
    Route::group(['prefix' => '/about'], function () {
        Route::get('getAll', [AboutController::class, 'getAbout']);
        Route::post('create', [AboutController::class, 'createAbout']);
        Route::delete('delete/{id}', [AboutController::class, 'deleteAbout']);
        Route::put('update/{id}', [AboutController::class, 'updateAbout']);
    });

    // activities routes
    Route::group(['prefix' => '/activities'], function () {
        Route::get('/getAll', [ActivitiesController::class, 'getActivities']);
        Route::post('/create', [ActivitiesController::class, 'createActivities']);
        Route::delete('/delete/{id}', [ActivitiesController::class, 'deleteActivity']);
        Route::put('/update/{id}', [ActivitiesController::class, 'updateActivity']);
    });

    // boards routes
    Route::group(['prefix' => '/boards'], function () {
        Route::get('/getAll', [BoardController::class, 'getBoard']);
        Route::post('/create', [BoardController::class, 'createBoard']);
        Route::delete('/delete/{id}', [BoardController::class, 'deleteBoard']);
        Route::put('/update/{id}', [BoardController::class, 'updateBoard']);
    });

    // headers routes
    Route::group(['prefix' => '/header'], function () {
        Route::get('/getAll', [HeadersController::class, 'getHeaders']);
        Route::post('/create', [HeadersController::class, 'createHeader']);
        Route::delete('/delete/{id}', [HeadersController::class, 'deleteHeader']);
        Route::put('/update/{id}', [HeadersController::class, 'updateHeader']);
    });

    // Projects routes
    Route::group(['prefix' => '/projects'], function () {
        Route::get('/getAll', [ProjectsController::class, 'getProjects']);
        Route::post('/create', [ProjectsController::class, 'createProject']);
        Route::delete('/delete/{id}', [ProjectsController::class, 'deleteProject']);
        Route::put('/update/{id}', [ProjectsController::class, 'updateProject']);
    });

    Route::group(['prefix' => '/members'], function () {
        Route::get('/', [MemberController::class, 'getMembers']);
        Route::delete('/{id}', [MemberController::class, 'deleteMember']);
    });

    Route::group(['prefix' => '/demandeEvent'], function () {
        Route::get('/getAll', [DemandeEventController::class, 'getDemandesEvent']);
       // Route::get('/resp',[DemandeEventController::class,'index']);
        //Route::get('/getOne/{id}', [DemandeEventController::class, 'getEvent']);
        Route::post('/create', [DemandeEventController::class, 'createDemandeEvent']);
        Route::put('/update/{id}', [DemandeEventController::class, 'updateDemandeEvent']);
        Route::delete('/delete/{id}', [DemandeEventController::class, 'deleteDemandeEvent']);
        Route::put('/accept/{id}', [DemandeEventController::class, 'accept']);
        Route::put('/decline/{id}', [DemandeEventController::class, 'decline']);
        Route::get('/getAllEvents', [DemandeEventController::class, 'show']);
    });
    

    

    // classes routes

    Route::group(['prefix' => '/Classe'], function () {
        Route::get('/', [ClasseController::class, 'getClasses']);
        Route::post('/', [ClasseController::class, 'createClasse']);
        Route::delete('/{id}', [ClasseController::class, 'deleteClass']);
        Route::put('/{id}', [ClasseController::class, 'updateClass']);
    });

});

// page club routes
Route::group(['prefix' => '/pc'], function () {
    Route::get('/header/{id}', [HeadersController::class, 'show']);
    Route::get('/activities/{id}', [ActivitiesController::class, 'show']);
    Route::get('/projects/{id}', [ProjectsController::class, 'show']);
    Route::get('/boards/{id}', [BoardController::class, 'show']);
    Route::get('/about/{id}', [AboutController::class, 'show']);
    Route::get('/fondator/{id}', [AboutController::class, 'getFondator']);
});

Route::group(['prefix' => '/m'], function () {
    Route::get('/M/{id}', [MaterielController::class, 'show']);
    Route::get('/{id}', [MaterielController::class, 'showMaterialsOfCategory']);
    Route::post('/{idM}/{idD}', [MaterielController::class, 'addMateriel']);
    Route::put('/Q/{idM}/{idD}', [MaterielController::class, 'setQuantity']);
    Route::delete('/{idM}/{idD}', [MaterielController::class, 'deleteMateriel']);
    Route::put('/{idM}/{idD}', [MaterielController::class, 'update']);
    Route::delete('/{id}', [MaterielController::class, 'delete']);
});
Route::get('/dac', [ClubController::class, 'show']);


Route::group(['prefix' => '/Salle'], function () {
    Route::get('/{id}', [SalleController::class, 'index']);
});
Route::group(['prefix' => '/Departement'], function () {
    Route::get('/', [DepartementController::class, 'index']);
});

Route::group(['prefix' => '/classe'], function () {
    Route::get('/{id}', [ClasseController::class, 'show']);
});

Route::group(['prefix' => '/s'], function () {
    Route::get('/', [StudentsController::class, 'show']);
    Route::post('/accept/{id}', [StudentsController::class, 'accept']);
    Route::post('/refuse/{id}', [StudentsController::class, 'refuse']);
});
