<?php

namespace App\Http\Controllers\club\pageClub;

use App\Http\Controllers\Controller;
use App\Models\project;
use Illuminate\Http\Request;
use App\Http\Requests\ProjectsRequest;

class ProjectsController extends Controller
{

    public function show($id)
    {
        $projects = project::where('idClub', $id)->get();
        if(empty($projects)){
            return response()->json(['message' => 'Aucun projet pour ce club'], 404);
        }
        return response()->json($projects, 200);
    }


    public function getProjects(Request $request){
        $club = $request->user()->club()->get('id');
        $project = project::where('idClub','=',$club[0]->id)->orderBy('updated_at')->paginate(5);
        if (sizeof($project) > 0)
            return response()->json(
                $project,
                200
            );
        else
            return response()->json([
                null
            ], 404);
    }
    public function createProject(ProjectsRequest $request){
        $club = $request->user()->club()->get('id');
        $affiche = $request->input('affiche');
         $idClub = $club[0]->id;

        $newProject = array(
            "affiche" => $affiche,
            "idClub" => $idClub
        );

        $createNewProject = project::create($newProject);

        if ($createNewProject) {
            return response()->json([
                'data' => [
                    'message' => 'Project created',
                    'id' => $createNewProject->id,
                    'attributes' => $createNewProject
                ]
            ], 201);
        }
        return "Header created";
    }

    public function deleteProject($idProject){
        $project = project::find($idProject);

        if ($project){
            $project->delete();
            return response()->json([
                'type' => 'Project',
                'message' => 'Project Supprimé! '
            ], 204);
        }
        else {
            return response()->json([
                'type' => 'Project',
                'message' => 'Project non trouvée'
            ], 404);
        }
    }

    public function updateProject($idProject, ProjectsRequest $request){

        $project = project::find($idProject);
        if ($project) {

            $project->affiche = $request->input('affiche') ? $request->input('affiche') : $project->affiche;
            $project->save();
            return response()->json([
                'message' => 'Project mis à jour',
                'id' => $project->id,
                'attributes' => $project
            ], 201);
        } else {
            return response()->json([
                "Project non trouvée"
            ], 404);
        }
    }

}
