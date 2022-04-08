<?php

namespace App\Http\Controllers;

use App\Models\project;
use Illuminate\Http\Request;
use App\Http\Requests\ProjectsRequest;

class ProjectsController extends Controller
{
    public function getProjects($id){

        $project = project::where('idClub','=',$id)->orderBy('updated_at')->paginate(5);
        if (sizeof($project) > 0)
            return response()->json(
                $project,
                200
            );
        else
            return response()->json([
                null
            ], 200);
    }
    public function createProject($id, ProjectsRequest $request){
        $affiche = $request->input('affiche');
         $idClub = $id;

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

    public function deleteProject($idClub,$idProject){
      
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

    public function updateProject($idClub, $idProject, ProjectsRequest $request){

        $project = project::find($idProject);
        if ($project) {
      
            $project->affiche = $request->input('affiche') ? $request->input('affiche') : $header->affiche;
            $project->idClub = $idClub;
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
