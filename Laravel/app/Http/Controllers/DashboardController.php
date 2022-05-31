<?php

namespace App\Http\Controllers;

use App\Models\DemandeMateriel;
use App\Models\DemandeSalle;
use App\Models\Matiere;
use App\Models\Note;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Date;
use Illuminate\Support\Facades\DB;

class DashboardController extends Controller
{
    public function get(Request $request)
    {

        if ($request->user()->roles()->get()->contains('name', "chefDepartement")) {
            $classes = $request->user()->department->classes()->count();
            $lastClass = $request->user()->department->classes()->orderBy('updated_at', 'desc')->first()?$request->user()->department->classes()->orderBy('updated_at', 'desc')->first()->updated_at:date('m/d/Y h:i:s a', time());
            $classes = array([
                'nbr' => $classes,
                'date' => $lastClass
            ]);

            // get number of students in all class of the departement
            $students = $request->user()->department->classes()->with("users")->get()->map(function ($item) {
                return $item->users->count();
            })->sum();
            // get last student in all class of the departement
            $lastStudent = User::whereHas('roles', function ($query) use ($request) {
                $query->where('name', 'student');
                $query->where('department', $request->user()->department->id);
            })->orderBy('updated_at', 'desc')->first()?User::whereHas('roles', function ($query) use ($request) {
                $query->where('name', 'student');
                $query->where('department', $request->user()->department->id);
            })->orderBy('updated_at', 'desc')->first()->updated_at:date('m/d/Y h:i:s a', time());
            $students = array([
                'nbr' => $students,
                'date' => $lastStudent
            ]);

            $teachers = DB::select("select count(*) from role_user WHERE role_id=7 and department = ?", [$request->user()->department->id]);
            // last inserted teacher of this departement
            $teacher = User::whereHas('roles', function ($query) use ($request) {
                $query->where('name', 'enseignant');
                $query->where('department', $request->user()->department->id);
            })->orderBy('updated_at', 'desc')->first()?User::whereHas('roles', function ($query) use ($request) {
                $query->where('name', 'enseignant');
                $query->where('department', $request->user()->department->id);
            })->orderBy('updated_at', 'desc')->first()->updated_at:date('m/d/Y h:i:s a', time());
            $teachers = ["nbr" => $teachers, "date" => $teacher];

            // get list of all classes of this departement
            $classesa = $request->user()->department->classes()->get();
            // get all ids from classes
            $ids = $classesa->map(function ($item) {
                return $item->id;
            });

            $matieres = Matiere::whereIn('idClasse', $ids)->with("notes")->get();

            // calculer les moyennes des notes
            $moyennes = $matieres->map(function ($item) {
                $notes = $item->notes->map(function ($item) {
                    return $item->note;
                });
                $moyenne = $notes->sum() / $notes->count();
                return $moyenne;
            });


            // get all ids of matieres
            $ids = $matieres->map(function ($item) {
                return $item->id;
            });

            $lastNote = Note::whereIn('matiere_id', $ids)->orderBy('updated_at', 'desc')->first();

            $moyennes=array(
                "nbr"=>$moyennes,
                "date"=>$lastNote->updated_at
            );

            $techniciens = User::whereHas('roles', function ($query) use ($request) {
                $query->where('name', 'technicien');
                $query->where('department', $request->user()->department->id);
            })->orderBy('updated_at', 'desc')->get();

            //  get all ids of techniciens
            $ids = $techniciens->map(function ($item) {
                return $item->id;
            });

            $ids = $ids->push($request->user()->id);

            // count all DemandeMateriel where idDestinataire in ids
            $dm = DemandeMateriel::whereIn('idDestinataire', $ids)->count();

            // get last DemandeMateriel where idDestinataire in ids
            $lastDm = DemandeMateriel::whereIn('idDestinataire', $ids)->orderBy('updated_at', 'desc')->first()?DemandeMateriel::whereIn('idDestinataire', $ids)->orderBy('updated_at', 'desc')->first()->updated_at: date('m/d/Y h:i:s a', time());


            // get all salles of this department
            $salles = $request->user()->department->salles()->get();

            $ids =  $salles->map(function ($item) {
                return $item->id;
            });

            $ds = DemandeSalle::whereIn('idSalle', $ids)->count();

            $last_ds = DemandeSalle::whereIn('idSalle', $ids)->orderBy('updated_at', 'desc')->first()?DemandeSalle::whereIn('idSalle', $ids)->orderBy('updated_at', 'desc')->first()->updated_at: date('m/d/Y h:i:s a', time());

            $dm = array(
                'nbr' => $dm,
                'date' => $lastDm
            );

            $ds=array(
                "nbr"=>$ds,
                "date"=>$last_ds,
            );

            $studentsFemmes = DB::select("select COUNT(*) FROM users JOIN role_user on users.id=role_user.user_id JOIN departements ON role_user.department=departements.id WHERE role_user.role_id=2 and sexe=0 and departements.id=? ; ", [$request->user()->department->id]);
            $studentsHomme = DB::select("select COUNT(*) FROM users JOIN role_user on users.id=role_user.user_id JOIN departements ON role_user.department=departements.id WHERE role_user.role_id=2 and sexe=1 and departements.id=? ; ", [$request->user()->department->id]);
            $hf = array(
                "h" => $studentsHomme[0],
                "f" => $studentsFemmes[0]
            );

            return response()->json(['classes' => $classes, 'students' => $students, 'teachers' => $teachers, 'moyennes' => $moyennes, "demandeMateriels" => $dm, "demandeSalles"=>$ds,"hf"=>$hf], 200);
        }
    }
}
